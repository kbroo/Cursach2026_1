package by.vsu.servlet;

import by.vsu.exception.ServiceException;
import by.vsu.model.User;
import by.vsu.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/auth/*")
public class AuthServlet extends HttpServlet {
    private final UserService userService = new UserService();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        HttpSession session = request.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;
        if (user != null) {
            response.sendRedirect(request.getContextPath() + "/profile");
            return;
        }

        if (path == null || path.equals("/")) {
            HttpSession sessionAuth = request.getSession();
            String error = (String) sessionAuth.getAttribute("error");
            String usernameOrEmail = (String) sessionAuth.getAttribute("usernameOrEmail");
            if (error != null) {
                request.setAttribute("error", error);
                sessionAuth.removeAttribute("error");
            }
            if (usernameOrEmail != null) {
                request.setAttribute("usernameOrEmail", usernameOrEmail);
                sessionAuth.removeAttribute("usernameOrEmail");
            }
            request.getRequestDispatcher("/WEB-INF/views/auth.jsp").forward(request, response);
            return;
        }
        if (path.equals("/registration")) {
            HttpSession sessionAuth = request.getSession();
            String error = (String) sessionAuth.getAttribute("error");
            String username = (String) sessionAuth.getAttribute("username");
            String email = (String) sessionAuth.getAttribute("email");
            if (error != null) {
                request.setAttribute("error", error);
                sessionAuth.removeAttribute("error");
            }
            if (username != null) {
                request.setAttribute("username", username);
                sessionAuth.removeAttribute("username");
            }
            if (email != null) {
                request.setAttribute("email", email);
                sessionAuth.removeAttribute("email");
            }
            request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
            return;
        }
        request.getRequestDispatcher("/WEB-INF/views/error/notFound.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        if ("/check-registration".equals(path)) {
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            User newUser;
            try {
                newUser = userService.register(username, email, password);
            } catch (ServiceException e) {
                request.getSession().setAttribute("error", e.getMessage());
                request.getSession().setAttribute("username", username);
                request.getSession().setAttribute("email", email);
                response.sendRedirect(request.getContextPath() + "/auth/registration");
                return;
            }
            HttpSession session = request.getSession(true);
            session.setAttribute("user", newUser);
            session.setMaxInactiveInterval(10 * 60);
            response.sendRedirect(request.getContextPath() + "/profile");
            return;
        }

        if ("/check-auth".equals(path)) {
            String usernameOrEmail = request.getParameter("usernameOrEmail");
            String password = request.getParameter("password");
            User user;
            try {
                if (usernameOrEmail.contains("@")) user = userService.loginWithEmail(usernameOrEmail, password);
                else user = userService.loginWithUserName(usernameOrEmail, password);
            } catch (ServiceException e) {
                request.getSession().setAttribute("error", e.getMessage());
                request.getSession().setAttribute("usernameOrEmail", usernameOrEmail);
                response.sendRedirect(request.getContextPath() + "/auth");
                return;
            }
            HttpSession session = request.getSession(true);
            session.setAttribute("user", user);
            session.setMaxInactiveInterval(10 * 60);
            response.sendRedirect(request.getContextPath() + "/profile");
            return;
        }
        request.getRequestDispatcher("/WEB-INF/views/error/notFound.jsp").forward(request, response);
    }
}

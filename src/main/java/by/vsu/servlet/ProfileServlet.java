package by.vsu.servlet;

import by.vsu.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/profile/*")
public class ProfileServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/auth/registration");
            return;
        }
        if (path == null || path.isEmpty() || "/".equals(path)) {
            String info = (String) session.getAttribute("info");
            if (info != null) {
                request.setAttribute("info", info);
                session.removeAttribute("info");
            }
            request.setAttribute("user", user);
            request.getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(request, response);
            return;
        }
        response.sendRedirect(request.getContextPath() + "/");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if ("/logout".equals(path)) {
            if (user != null) {
                session.invalidate();
            }
            response.sendRedirect(request.getContextPath());
            return;
        }
        response.sendRedirect(request.getContextPath() + "/");
    }
}

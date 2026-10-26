package by.vsu.servlet;

import by.vsu.dto.Cart;
import by.vsu.exception.ServiceException;
import by.vsu.model.CartItem;
import by.vsu.model.User;
import by.vsu.service.CartService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/cart/*")
public class CartServlet extends HttpServlet {
    private final CartService cartService = new CartService();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String path = request.getPathInfo();
        if (path == null || path.isEmpty() || "/".equals(path)) {
            HttpSession session = request.getSession();
            User user = (session != null) ? (User) session.getAttribute("user") : null;
            if (user == null) {
                response.sendRedirect(request.getContextPath() + "/auth");
                return;
            }
            Cart cart = cartService.getCartOfUser(user.getUserId());
            request.setAttribute("user", user);
            request.setAttribute("cart", cart);
            request.getRequestDispatcher("/WEB-INF/views/cart.jsp").forward(request, response);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String path = request.getPathInfo();
        HttpSession session = request.getSession();
        if ("/action".equals(path)) {
            String action = request.getParameter("action");
            String strProductId = request.getParameter("productId");
            User user = (User) session.getAttribute("user");
            if (user == null) {
                response.sendRedirect(request.getContextPath() + "/auth");
                return;
            }

            int productId;
            try {
                productId = Integer.parseInt(strProductId);
            } catch (NumberFormatException e) {
                response.sendRedirect(request.getContextPath());
                return;
            }
            try {
                if ("+".equals(action)) {
                    cartService.increaseValueInCart(user.getUserId(), productId, 1);
                } else if ("-".equals(action)) {
                    cartService.decreaseValueInCart(user.getUserId(), productId, 1);
                }
            } catch (ServiceException e) {
                session.setAttribute("error", e.getMessage());
            }

            response.sendRedirect(request.getContextPath() + "/cart");
        }
    }
}

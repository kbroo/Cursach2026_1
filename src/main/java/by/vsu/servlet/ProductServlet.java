package by.vsu.servlet;

import by.vsu.exception.ServiceException;
import by.vsu.model.CartItem;
import by.vsu.model.Product;
import by.vsu.model.User;
import by.vsu.service.CartService;
import by.vsu.service.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/product/*")
public class ProductServlet extends HttpServlet {
    private final ProductService productService = new ProductService();
    private final CartService cartService = new CartService();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        if (path == null || "/".equals(path) || path.isEmpty()) {
            HttpSession session = request.getSession(false);
            try {
                int productId = Integer.parseInt(request.getParameter("id"));
                Product product = productService.getById(productId);

                int quantityOfProductInCart = 0;

                if (session != null && session.getAttribute("user") != null) {
                    User user = (User) session.getAttribute("user");
                    CartItem cartItem = cartService.getCartItem(user.getUserId(), product.getId());
                    if (cartItem != null) {
                        quantityOfProductInCart = cartItem.getQuantity();
                    }
                }

                request.setAttribute("quantityOfProductInCart", quantityOfProductInCart);
                request.setAttribute("product", product);
                request.getRequestDispatcher("/WEB-INF/views/product.jsp").forward(request, response);
                if (session != null && session.getAttribute("error") != null) {
                    session.removeAttribute("error");
                }

            } catch (NumberFormatException e) {
                request.setAttribute("error", "Ошибка получения товара");
                request.getRequestDispatcher("/WEB-INF/views/product.jsp").forward(request, response);
            }
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        HttpSession session = request.getSession();
        if ("/action".equals(path)) {
            String action = request.getParameter("action");
            String strProductId = request.getParameter("productId");
            User user = (User) session.getAttribute("user");
            if (user == null) {
                response.sendRedirect(request.getContextPath() + "/product?id=" + strProductId);
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

            response.sendRedirect(request.getContextPath() + "/product?id=" + strProductId);
        }

    }
}

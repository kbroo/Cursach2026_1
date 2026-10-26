package by.vsu.servlet;

import by.vsu.exception.ServiceException;
import by.vsu.model.Product;
import by.vsu.service.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("")
public class MainServlet extends HttpServlet {
    private final ProductService productService = new ProductService();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String search = request.getParameter("search");

        List<Product> products;
        try {
            products = productService.getLikeName(search);
        } catch (ServiceException e) {
            request.setAttribute("isMainPage", true);
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }
        request.setAttribute("isMainPage", true);
        request.setAttribute("products", products);
        request.setAttribute("searchQuery", search != null ? search : "");

        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}

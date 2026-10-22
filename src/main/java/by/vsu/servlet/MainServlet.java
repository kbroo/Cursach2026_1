package by.vsu.servlet;

import by.vsu.exception.ServiceException;
import by.vsu.model.Product;
import by.vsu.service.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("")
public class MainServlet extends HttpServlet {
    private final ProductService productService = new ProductService();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products = List.of();
        try {
            products = productService.getProducts(20);
        } catch (ServiceException e) {
            request.setAttribute("error", e);
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }
        request.setAttribute("products", products);
        request.getRequestDispatcher("index.jsp").forward(request, response);
        return;
    }
}

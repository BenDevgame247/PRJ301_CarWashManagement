package controller;

import dao.CustomerProfileDAO;
import dto.CustomerProfileDTO;
import dto.UserDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "CustomerProfileServlet", urlPatterns = {"/profile"})
public class CustomerProfileServlet extends HttpServlet {

    private final CustomerProfileDAO customerProfileDAO = new CustomerProfileDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        UserDTO user = (UserDTO) session.getAttribute("user");
        if (!"CUSTOMER".equalsIgnoreCase(user.getRole())) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        CustomerProfileDTO profile = customerProfileDAO.getProfileByUserId(user.getUserId());
        if (profile == null) {
            request.setAttribute("profileError", "Customer profile data was not found.");
        } else {
            request.setAttribute("profile", profile);
        }

        request.getRequestDispatcher("/profile.jsp").forward(request, response);
    }
}

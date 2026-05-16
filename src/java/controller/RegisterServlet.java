
package controller;

import dao.RegisterDAO;
import dto.RegisterDTO;
import utils.RegisterValidator;
import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {
    
    private final RegisterDAO registerDAO = new RegisterDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/register.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String plateNumber = request.getParameter("plateNumber");
        String brand = request.getParameter("brand");
        String model = request.getParameter("model");
        String color = request.getParameter("color");
        
        RegisterDTO registerDTO = new RegisterDTO(
                fullName,
                email,
                password,
                phone,
                plateNumber,
                brand,
                model,
                color
        );
        
        RegisterValidator validator = new RegisterValidator();
        String error = validator.validate(registerDTO);
        
        if (error != null) {
            request.setAttribute("error", error);
            request.setAttribute("register", registerDTO);
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }
        
        if (registerDAO.isEmailExist(email)) {
            request.setAttribute("error", "Email already exists.");
            request.setAttribute("register", registerDTO);
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }
        
        if (registerDAO.isPlateNumberExist(plateNumber)) {
            request.setAttribute("error", "Plate number already exists.");
            request.setAttribute("register", registerDTO);
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }
        
        boolean success = registerDAO.registerCustomer(registerDTO);
        
        if (success) {
            response.sendRedirect(request.getContextPath() + "/login?register=success");
            return;
        } else {
            request.setAttribute("error", "Register failed. Please try again.");
            request.setAttribute("register", registerDTO);
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }
    }
}

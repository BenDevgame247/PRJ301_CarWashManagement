/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.UserDAO;
import dto.UserDTO;
import dto.LoginDTO;
import utils.LoginValidator;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 *
 * @author MY PC
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        LoginDTO loginDTO = new LoginDTO(email, password);
        LoginValidator validator = new LoginValidator();
        String error = validator.validate(loginDTO);

        if (error != null) {
            request.setAttribute("error", error);
            request.setAttribute("login", loginDTO);
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }
 
        UserDTO user = userDAO.login(email.trim(), password.trim());

        if (user == null) {
            request.setAttribute("error", "Invalid email or password.");
            request.setAttribute("login", loginDTO);
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        if ("CUSTOMER".equalsIgnoreCase(user.getRole())) {
            response.sendRedirect(request.getContextPath() + "/customer/profile");
        } else if ("ADMIN".equalsIgnoreCase(user.getRole())) {
            response.sendRedirect(request.getContextPath() + "/admin/dashboard");
        } else {
            response.sendRedirect(request.getContextPath() + "/");
        }
    }
}

package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.DBContext;

@WebServlet(name = "DatabaseTestServlet", urlPatterns = {"/db-test"})
public class DatabaseTestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html><head><meta charset='UTF-8'><title>Database Test</title></head><body>");

            try (Connection connection = new DBContext().getConnection();
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) AS total_users FROM Users")) {

                DatabaseMetaData metaData = connection.getMetaData();
                int totalUsers = 0;
                if (resultSet.next()) {
                    totalUsers = resultSet.getInt("total_users");
                }

                out.println("<h1>Database connection successful</h1>");
                out.println("<p>Database: " + escape(metaData.getDatabaseProductName()) + "</p>");
                out.println("<p>Driver: " + escape(metaData.getDriverName()) + "</p>");
                out.println("<p>Total users: " + totalUsers + "</p>");
            } catch (Exception ex) {
                out.println("<h1>Database connection failed</h1>");
                out.println("<p>" + escape(ex.getClass().getSimpleName()) + ": " + escape(ex.getMessage()) + "</p>");
                out.println("<p>Check SQL Server is running, the JDBC driver is in WEB-INF/lib, and DB_USER/DB_PASSWORD are correct.</p>");
            }

            out.println("</body></html>");
        }
    }

    private String escape(String value) {
        if (value == null) {
            return "";
        }
        return value
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }
}

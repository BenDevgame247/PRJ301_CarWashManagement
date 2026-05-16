package controller;

import dao.CustomerProfileDAO;
import dto.CustomerProfileDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ProfileTestServlet", urlPatterns = {"/profile-test"})
public class ProfileTestServlet extends HttpServlet {

    private final CustomerProfileDAO customerProfileDAO = new CustomerProfileDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html><head><meta charset='UTF-8'><title>Profile Test</title>");
            out.println("<style>");
            out.println("body{font-family:Arial,sans-serif;max-width:760px;margin:40px auto;line-height:1.5}");
            out.println("table{border-collapse:collapse;width:100%}td,th{border:1px solid #ddd;padding:10px;text-align:left}");
            out.println("th{width:220px;background:#f5f5f5}.error{color:#b00020}");
            out.println("</style></head><body>");

            Integer userId = parseUserId(request.getParameter("userId"));
            if (userId == null) {
                out.println("<h1 class='error'>Missing or invalid userId</h1>");
                out.println("<p>Try: <code>profile-test?userId=1</code></p>");
                out.println("</body></html>");
                return;
            }

            CustomerProfileDTO profile = customerProfileDAO.getProfileByUserId(userId);
            if (profile == null) {
                out.println("<h1 class='error'>Profile not found</h1>");
                out.println("<p>No active customer profile found for userId = " + userId + ".</p>");
                out.println("</body></html>");
                return;
            }

            out.println("<h1>Customer Profile Test</h1>");
            out.println("<table>");
            printRow(out, "User ID", String.valueOf(profile.getUserId()));
            printRow(out, "Customer ID", String.valueOf(profile.getCustomerId()));
            printRow(out, "Full name", profile.getFullName());
            printRow(out, "Email", profile.getEmail());
            printRow(out, "Phone", profile.getPhone());
            printRow(out, "Vehicle ID", String.valueOf(profile.getVehicleId()));
            printRow(out, "Brand", profile.getBrand());
            printRow(out, "Model", profile.getModel());
            printRow(out, "Color", profile.getColor());
            printRow(out, "Plate number", profile.getPlateNumber());
            printRow(out, "Tier", profile.getTierName());
            printRow(out, "Current points", String.valueOf(profile.getCurrentPoints()));
            printRow(out, "Lifetime points", String.valueOf(profile.getLifetimePoints()));
            printRow(out, "Next reward", profile.getRewardName() == null ? "No next reward" : profile.getRewardName());
            printRow(out, "Required points", String.valueOf(profile.getRequiredPoints()));
            printRow(out, "Points to next reward", String.valueOf(profile.getPointsToNextReward()));
            out.println("</table>");
            out.println("</body></html>");
        }
    }

    private Integer parseUserId(String rawUserId) {
        if (rawUserId == null || rawUserId.trim().isEmpty()) {
            return null;
        }
        try {
            return Integer.parseInt(rawUserId.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private void printRow(PrintWriter out, String label, String value) {
        out.println("<tr><th>" + escape(label) + "</th><td>" + escape(value) + "</td></tr>");
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

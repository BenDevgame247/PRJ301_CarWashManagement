<%@page import="dto.CustomerProfileDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    CustomerProfileDTO profile = (CustomerProfileDTO) request.getAttribute("profile");
    String profileError = (String) request.getAttribute("profileError");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Customer Profile</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                max-width: 860px;
                margin: 40px auto;
                line-height: 1.5;
                color: #222;
            }
            .header {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-bottom: 24px;
            }
            table {
                border-collapse: collapse;
                width: 100%;
                margin-bottom: 22px;
            }
            th, td {
                border: 1px solid #ddd;
                padding: 10px;
                text-align: left;
            }
            th {
                width: 230px;
                background: #f5f5f5;
            }
            .error {
                color: #b00020;
            }
            a {
                color: #0b57d0;
            }
        </style>
    </head>
    <body>
        <div class="header">
            <h1>Customer Profile</h1>
            <a href="${pageContext.request.contextPath}/logout">Logout</a>
        </div>

        <% if (profileError != null) { %>
            <p class="error"><%= profileError %></p>
            <p>Please check that this user has customer, loyalty, and tier data in the database.</p>
        <% } else if (profile != null) { %>
            <h2>Account</h2>
            <table>
                <tr><th>Full name</th><td><%= profile.getFullName() %></td></tr>
                <tr><th>Email</th><td><%= profile.getEmail() %></td></tr>
                <tr><th>Phone</th><td><%= profile.getPhone() %></td></tr>
            </table>

            <h2>Vehicle</h2>
            <table>
                <tr><th>Plate number</th><td><%= profile.getPlateNumber() == null ? "No active vehicle" : profile.getPlateNumber() %></td></tr>
                <tr><th>Brand</th><td><%= profile.getBrand() == null ? "" : profile.getBrand() %></td></tr>
                <tr><th>Model</th><td><%= profile.getModel() == null ? "" : profile.getModel() %></td></tr>
                <tr><th>Color</th><td><%= profile.getColor() == null ? "" : profile.getColor() %></td></tr>
            </table>

            <h2>Loyalty</h2>
            <table>
                <tr><th>Tier</th><td><%= profile.getTierName() %></td></tr>
                <tr><th>Current points</th><td><%= profile.getCurrentPoints() %></td></tr>
                <tr><th>Lifetime points</th><td><%= profile.getLifetimePoints() %></td></tr>
                <tr><th>Next reward</th><td><%= profile.getRewardName() == null ? "No next reward" : profile.getRewardName() %></td></tr>
                <tr><th>Required points</th><td><%= profile.getRequiredPoints() %></td></tr>
                <tr><th>Points to next reward</th><td><%= profile.getPointsToNextReward() %></td></tr>
            </table>
        <% } %>
    </body>
</html>

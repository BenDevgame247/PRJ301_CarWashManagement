<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if (session.getAttribute("user") == null) {
        response.sendRedirect(request.getContextPath() + "/login");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head><title>Book a Car Wash</title></head>
<body>

<h2>Đặt lịch rửa xe</h2>

<% if (request.getAttribute("bookingError") != null) { %>
    <p style="color:red"><%= request.getAttribute("bookingError") %></p>
<% } %>
<% if (request.getAttribute("bookingSuccess") != null) { %>
    <p style="color:green">
        <%= request.getAttribute("bookingSuccess") %>
        (Booking ID: <%= request.getAttribute("bookingId") %>)
    </p>
<% } %>

<form method="POST" action="${pageContext.request.contextPath}/booking">
    <label>Customer ID:</label><br>
    <input type="number" name="customerId" value="1" /><br><br>

    <label>Vehicle ID:</label><br>
    <input type="number" name="vehicleId" value="1" /><br><br>

    <label>Service ID:</label><br>
    <input type="number" name="serviceId" value="1" /><br><br>

    <label>Ngày đặt:</label><br>
    <input type="date" name="bookingDate" /><br><br>

    <label>Giờ đặt:</label><br>
    <input type="time" name="bookingTime" /><br><br>

    <label>Ghi chú:</label><br>
    <textarea name="note"></textarea><br><br>

    <input type="submit" value="Đặt lịch" />
</form>

</body>
</html>
package controller;

import dto.BookingRequestDTO;
import dto.UserDTO;
import service.BookingService;
import service.BookingService.BookingResult;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * CONTROL layer cho chức năng booking.
 * Servlet CHỈ làm 3 việc: đọc request -> gọi Service -> forward kết quả.
 * KHÔNG được viết logic so sánh ngày / tính tiền trực tiếp ở đây
 * (đó là trách nhiệm của BookingService - Logic Tier).
 */
@WebServlet(name = "BookingServlet", urlPatterns = {"/booking"})
public class BookingServlet extends HttpServlet {

    private final BookingService bookingService = new BookingService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Hiển thị form booking (cần customer đã login)
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        request.getRequestDispatcher("/booking.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        UserDTO user = (UserDTO) session.getAttribute("user");

        try {
            // --- Đọc dữ liệu form, đóng gói vào DTO ---
            int customerId = Integer.parseInt(request.getParameter("customerId"));
            int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
            int serviceId = Integer.parseInt(request.getParameter("serviceId"));
            Date bookingDate = Date.valueOf(request.getParameter("bookingDate")); // yyyy-MM-dd
            Time bookingTime = Time.valueOf(request.getParameter("bookingTime") + ":00"); // HH:mm -> HH:mm:ss
            String note = request.getParameter("note");

            BookingRequestDTO req = new BookingRequestDTO(
                    customerId, vehicleId, serviceId, null, bookingDate, bookingTime, note
            );

            // --- Gọi Logic Tier để xử lý toàn bộ flow ---
            BookingResult result = bookingService.createBooking(req);

            if (!result.isSuccess()) {
                request.setAttribute("bookingError", result.getMessage());
                request.getRequestDispatcher("/booking.jsp").forward(request, response);
                return;
            }

            request.setAttribute("bookingSuccess", result.getMessage());
            request.setAttribute("bookingId", result.getBookingId());
            request.getRequestDispatcher("/booking.jsp").forward(request, response);

        } catch (IllegalArgumentException e) {
            // Lỗi parse số/ngày/giờ từ form (dữ liệu nhập sai định dạng)
            request.setAttribute("bookingError", "Invalid input format. Please check the form.");
            request.getRequestDispatcher("/booking.jsp").forward(request, response);
        }
    }
}

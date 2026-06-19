package service;

import dao.BookingDAO;
import dao.LoyaltyTierDAO;
import dto.BookingRequestDTO;
import dto.TierInfoDTO;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

/**
 * LOGIC TIER + CONTROL cho chức năng Booking.
 *
 * Đây là nơi chứa "bộ não" quyết định: có cho khách đặt lịch hay không,
 * và điều phối toàn bộ flow tạo booking (validate -> tính tiền -> lưu DB).
 *
 * Servlet (Control layer phía trên) CHỈ gọi các hàm public ở đây,
 * không tự viết logic so sánh ngày / tính tiền trong servlet.
 */
public class BookingService {

    private final LoyaltyTierDAO tierDAO = new LoyaltyTierDAO();
    private final BookingDAO bookingDAO = new BookingDAO();

    /**
     * Kết quả trả về cho mọi hành động của service: thành công hay không,
     * kèm message giải thích lý do (để Servlet đưa ra JSP hiển thị).
     */
    public static class BookingResult {
        private final boolean success;
        private final String message;
        private final Integer bookingId; // chỉ có giá trị khi success = true

        public BookingResult(boolean success, String message, Integer bookingId) {
            this.success = success;
            this.message = message;
            this.bookingId = bookingId;
        }

        public boolean isSuccess() { return success; }
        public String getMessage() { return message; }
        public Integer getBookingId() { return bookingId; }
    }

    /**
     * Kiểm tra xem customer có được phép đặt lịch vào ngày `requestedDate` không.
     *
     * Quy tắc:
     *  1. Customer phải có loyalty tier (nếu không -> false).
     *  2. Ngày đặt không được là ngày trong quá khứ.
     *  3. Ngày đặt không được vượt quá (hôm nay + booking_days_ahead của tier).
     *
     * @param customerId    id khách hàng
     * @param requestedDate ngày khách muốn đặt lịch rửa xe
     * @return true nếu hợp lệ, false nếu vi phạm bất kỳ điều kiện trên
     */
    public boolean canBook(int customerId, LocalDate requestedDate) {
        if (requestedDate == null) {
            return false;
        }

        TierInfoDTO tier = tierDAO.getTierByCustomerId(customerId);
        if (tier == null) {
            // Customer chưa có loyalty account -> không xác định được quyền booking
            return false;
        }

        LocalDate today = LocalDate.now();

        // Không cho đặt ngày trong quá khứ
        if (requestedDate.isBefore(today)) {
            return false;
        }

        LocalDate maxAllowedDate = today.plusDays(tier.getBookingDaysAhead());

        // Vượt quá số ngày tier cho phép -> không hợp lệ
        return !requestedDate.isAfter(maxAllowedDate);
    }

    /**
     * Bản overload tiện dùng khi Servlet đang cầm java.sql.Date (từ form HTML).
     */
    public boolean canBook(int customerId, Date requestedSqlDate) {
        if (requestedSqlDate == null) {
            return false;
        }
        return canBook(customerId, requestedSqlDate.toLocalDate());
    }

    /**
     * Điều phối toàn bộ flow tạo booking:
     *  1. Validate quyền đặt lịch qua canBook()
     *  2. Nếu hợp lệ -> lấy giá service, tính tiền (tạm chưa áp dụng discount phức tạp,
     *     phần discount/promotion có thể nối thêm khi nhóm hoàn thiện Loyalty Engine)
     *  3. Gọi DAO lưu booking với status PENDING
     *
     * @param req thông tin booking khách gửi lên (đã đóng gói từ Servlet)
     * @return BookingResult chứa success/message/bookingId
     */
    public BookingResult createBooking(BookingRequestDTO req) {
        if (req == null) {
            return new BookingResult(false, "Booking request is missing.", null);
        }

        // 1. Validate điều kiện booking theo tier
        boolean allowed = canBook(req.getCustomerId(), req.getBookingDate());
        if (!allowed) {
            return new BookingResult(false,
                    "Booking date is invalid: it is either in the past or exceeds your tier's allowed booking window.",
                    null);
        }

        // 2. Lấy giá service để tính tiền
        BigDecimal originalPrice = bookingDAO.getServicePrice(req.getServiceId());
        if (originalPrice == null) {
            return new BookingResult(false, "Selected service package is not available.", null);
        }

        // TODO: phần discount theo promotion/tier sẽ do module Loyalty Engine cung cấp.
        // Hiện tại tạm để discount = 0 để không chặn flow chính của booking.
        BigDecimal discountAmount = BigDecimal.ZERO;
        BigDecimal finalAmount = originalPrice.subtract(discountAmount);

        // 3. Lưu booking
        int bookingId = bookingDAO.insertBooking(req, originalPrice, discountAmount, finalAmount);

        if (bookingId == -1) {
            return new BookingResult(false, "Failed to save booking. Please try again.", null);
        }

        return new BookingResult(true, "Booking created successfully.", bookingId);
    }
}

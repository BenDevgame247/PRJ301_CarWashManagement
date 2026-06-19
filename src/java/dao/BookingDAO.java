package dao;

import dto.BookingRequestDTO;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import utils.DBContext;

/**
 * DAO chỉ làm việc ghi/đọc bảng Bookings.
 * KHÔNG kiểm tra canBook ở đây — việc đó đã được BookingService làm
 * TRƯỚC KHI gọi tới DAO này. DAO chỉ "tin tưởng" dữ liệu đưa vào là hợp lệ.
 */
public class BookingDAO {

    /**
     * Insert một booking mới với status = 'PENDING'.
     * @return booking_id vừa tạo, hoặc -1 nếu thất bại
     */
    public int insertBooking(BookingRequestDTO req, BigDecimal originalPrice,
            BigDecimal discountAmount, BigDecimal finalAmount) {

        String sql = "INSERT INTO dbo.Bookings "
                + "(customer_id, vehicle_id, service_id, promotion_id, booking_date, booking_time, "
                + "status, original_price, discount_amount, final_amount, note) "
                + "VALUES (?, ?, ?, ?, ?, ?, 'PENDING', ?, ?, ?, ?)";

        try (Connection conn = new DBContext().getConnection();
                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, req.getCustomerId());
            ps.setInt(2, req.getVehicleId());
            ps.setInt(3, req.getServiceId());

            if (req.getPromotionId() != null) {
                ps.setInt(4, req.getPromotionId());
            } else {
                ps.setNull(4, java.sql.Types.INTEGER);
            }

            ps.setDate(5, req.getBookingDate());
            ps.setTime(6, req.getBookingTime());
            ps.setBigDecimal(7, originalPrice);
            ps.setBigDecimal(8, discountAmount);
            ps.setBigDecimal(9, finalAmount);
            ps.setString(10, req.getNote());

            int affected = ps.executeUpdate();
            if (affected > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Lấy giá service để tính originalPrice (BookingService cần số liệu này
     * trước khi insert). Đặt ở đây vì vẫn là truy vấn DB thuần.
     */
    public BigDecimal getServicePrice(int serviceId) {
        String sql = "SELECT price FROM dbo.ServicePackages WHERE service_id = ? AND status = 'ACTIVE'";

        try (Connection conn = new DBContext().getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, serviceId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getBigDecimal("price");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

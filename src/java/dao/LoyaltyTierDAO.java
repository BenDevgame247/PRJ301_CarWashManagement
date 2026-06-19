package dao;

import dto.TierInfoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import utils.DBContext;

/**
 * DAO chỉ chịu trách nhiệm truy vấn DB liên quan tới Loyalty Tier.
 * KHÔNG chứa logic so sánh ngày / quyết định cho phép booking hay không
 * -> logic đó thuộc về BookingService (Logic Tier).
 */
public class LoyaltyTierDAO {

    /**
     * Lấy tier hiện tại của một customer.
     * @param customerId id của khách hàng
     * @return TierInfoDTO nếu tìm thấy, null nếu customer chưa có loyalty account
     */
    public TierInfoDTO getTierByCustomerId(int customerId) {
        String sql = "SELECT lt.tier_id, lt.tier_name, lt.booking_days_ahead "
                + "FROM dbo.LoyaltyAccounts la "
                + "INNER JOIN dbo.LoyaltyTiers lt ON la.tier_id = lt.tier_id "
                + "WHERE la.customer_id = ?";

        try (Connection conn = new DBContext().getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, customerId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new TierInfoDTO(
                            rs.getInt("tier_id"),
                            rs.getString("tier_name"),
                            rs.getInt("booking_days_ahead")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

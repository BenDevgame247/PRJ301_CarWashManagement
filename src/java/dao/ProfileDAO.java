package dao;

import dto.ProfileDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import utils.DBContext;

public class ProfileDAO {

    public ProfileDTO getProfileByUserId(int userId) {
        String sql = "SELECT TOP 1 "
                + "u.user_id, c.customer_id, u.full_name, u.email, u.phone, "
                + "v.vehicle_id, v.brand, v.model, v.color, v.plate_number, "
                + "lt.tier_id, lt.tier_name, la.current_points, la.lifetime_points, "
                + "next_reward.reward_id, next_reward.reward_name, next_reward.required_points, "
                + "CASE "
                + "    WHEN next_reward.required_points IS NULL THEN 0 "
                + "    ELSE next_reward.required_points - la.current_points "
                + "END AS points_to_next_reward "
                + "FROM dbo.Users u "
                + "INNER JOIN dbo.Customers c ON u.user_id = c.user_id "
                + "LEFT JOIN dbo.Vehicles v ON c.customer_id = v.customer_id AND v.status = 'ACTIVE' "
                + "INNER JOIN dbo.LoyaltyAccounts la ON c.customer_id = la.customer_id "
                + "INNER JOIN dbo.LoyaltyTiers lt ON la.tier_id = lt.tier_id "
                + "OUTER APPLY ( "
                + "    SELECT TOP 1 r.reward_id, r.reward_name, r.required_points "
                + "    FROM dbo.Rewards r "
                + "    WHERE r.status = 'ACTIVE' AND r.required_points > la.current_points "
                + "    ORDER BY r.required_points ASC "
                + ") next_reward "
                + "WHERE u.user_id = ? AND u.role = 'CUSTOMER' AND u.status = 'ACTIVE' "
                + "ORDER BY v.vehicle_id ASC";

        try (Connection conn = new DBContext().getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ProfileDTO profile = new ProfileDTO();
                    profile.setUserId(rs.getInt("user_id"));
                    profile.setCustomerId(rs.getInt("customer_id"));
                    profile.setFullName(rs.getString("full_name"));
                    profile.setEmail(rs.getString("email"));
                    profile.setPhone(rs.getString("phone"));

                    profile.setVehicleId(rs.getInt("vehicle_id"));
                    profile.setBrand(rs.getString("brand"));
                    profile.setModel(rs.getString("model"));
                    profile.setColor(rs.getString("color"));
                    profile.setPlateNumber(rs.getString("plate_number"));

                    profile.setTierId(rs.getInt("tier_id"));
                    profile.setTierName(rs.getString("tier_name"));
                    profile.setCurrentPoints(rs.getInt("current_points"));
                    profile.setLifetimePoints(rs.getInt("lifetime_points"));

                    profile.setRewardId(rs.getInt("reward_id"));
                    profile.setRewardName(rs.getString("reward_name"));
                    profile.setRequiredPoints(rs.getInt("required_points"));
                    profile.setPointsToNextReward(rs.getInt("points_to_next_reward"));
                    return profile;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}

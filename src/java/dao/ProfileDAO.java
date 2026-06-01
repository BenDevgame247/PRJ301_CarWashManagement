package dao;

import dto.ProfileDTO;
import dto.RecentActivityDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import utils.DBContext;

public class ProfileDAO {

    public ProfileDTO getProfileByUserId(int userId) {
        String sql = 
                "SELECT " +
                "u.user_id, " +
                "c.customer_id, " +
                "u.full_name, " +
                "u.email, " +
                "u.phone, " +
                "u.nick_name " +
                "FROM dbo.Users u " +
                "INNER JOIN dbo.Customers c ON u.user_id = c.user_id " +
                "WHERE u.user_id = ? " +
                "AND u.role = 'CUSTOMER' " +
                "AND u.status = 'ACTIVE'";
        
        String sql2 = 
                "SELECT " +
                "c.total_washes, " +
                "ISNULL(monthly.monthly_paid, 0) AS monthly_paid, " +
                "ISNULL(current_tier.tier_name, 'Member') AS tier_rank, " +
                "next_tier.tier_name AS next_tier_rank, " +
                "CASE " +
                "    WHEN next_tier.tier_id IS NULL THEN 0 " +
                "    WHEN next_tier.min_washes - c.total_washes < 0 THEN 0 " +
                "    ELSE next_tier.min_washes - c.total_washes " +
                "END AS washes_to_next_tier, " +
                "4.8 AS customer_rating " +
                "FROM dbo.Customers c " +
                "LEFT JOIN dbo.LoyaltyAccounts la ON c.customer_id = la.customer_id " +
                "LEFT JOIN dbo.LoyaltyTiers current_tier ON la.tier_id = current_tier.tier_id " +
                "OUTER APPLY ( " +
                "    SELECT TOP 1 lt.tier_id, lt.tier_name, lt.min_washes " +
                "    FROM dbo.LoyaltyTiers lt " +
                "    WHERE lt.min_washes > c.total_washes " +
                "    ORDER BY lt.min_washes ASC " +
                ") next_tier " +
                "OUTER APPLY ( " +
                "    SELECT SUM(b.final_amount) AS monthly_paid " +
                "    FROM dbo.Bookings b " +
                "    WHERE b.customer_id = c.customer_id " +
                "    AND b.status = 'COMPLETED' " +
                "    AND YEAR(b.booking_date) = YEAR(GETDATE()) " +
                "    AND MONTH(b.booking_date) = MONTH(GETDATE()) " +
                ") monthly " +
                "WHERE c.customer_id = ?";
        
        String sql3 = 
                "SELECT TOP 5 " +
                "b.booking_id, sp.server_name, v.brand, v.model, v.plate_number, b.status, b.final_amount, b.booking_date, b.booking_time " +
                "FROM dbo.Bookings b " +
                "INNER JOIN dbo.ServicePackages sp ON b.service_id = sp.service_id " +
                "INNER JOIN dbo.Vehicles v ON b.vehicle_id = v.vehicle_id " +
                "WHERE b.customer_id = ? " +
                "ORDER BY b.booking_date DESC, b.booking_time DESC";
        
       try (Connection conn = new DBContext().getConnection()) {
           ProfileDTO profile = null;
           
           try (PreparedStatement ps = conn.prepareStatement(sql)) {
               ps.setInt(1, userId);
               
               try (ResultSet rs = ps.executeQuery()) {
                   if (rs.next()) {
                       profile = new ProfileDTO();
                       profile.setUserId(rs.getInt("user_id"));
                       profile.setCustomerId(rs.getInt("customer_id"));
                       profile.setFullName(rs.getString("full_name"));
                       profile.setNickName(rs.getString("nick_name"));
                       profile.setEmail(rs.getString("email"));
                       profile.setPhone(rs.getString("phone"));
                   }
               }
           }
           
           if (profile == null) {
               return null;
           }
           
           try (PreparedStatement ps = conn.prepareStatement(sql2)) {
               ps.setInt(1, profile.getCustomerId());
               
               try (ResultSet rs = ps.executeQuery()) {
                   if (rs.next()) {
                       profile.setTotalWashes(rs.getInt("total_washes"));
                       profile.setMonthlyPaid(rs.getBigDecimal("monthly_paid"));
                       profile.setTierRank(rs.getString("tier_name"));
                       profile.setCustomerRating(rs.getDouble("customer_rating"));
                   }
               }
           }
           
           List<RecentActivityDTO> activities = new ArrayList<>();
           
           try (PreparedStatement ps = conn.prepareStatement(sql3)) {
               ps.setInt(1, profile.getCustomerId());
               
               try (ResultSet rs = ps.executeQuery()) {
                   if (rs.next()) {
                       RecentActivityDTO activity = new RecentActivityDTO();
                       
                       activity.setBookingId(rs.getInt("booking_id"));
                       activity.setServiceName(rs.getString("service_name"));
                       activity.setVehicleName(rs.getString("brand") + " " + rs.getString("model"));
                       activity.setPlateNumber(rs.getString("plate_number"));
                       activity.setStatus(rs.getString("status"));
                       activity.setFinalAmount(rs.getBigDecimal("final_amount"));
                       activity.setBookingDate(rs.getDate("booking_date"));
                       activity.setBookingTime(rs.getTime("booking_time"));
                       
                       activities.add(activity);
                       
                   }
               }
           }
           
           profile.setRecentActivity(activities);
           return profile;
           
       } catch (Exception e) {
           e.printStackTrace();
       }
        
        return null;
    }
}

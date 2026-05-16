
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import utils.DBContext;
import dto.RegisterDTO;

public class RegisterDAO {
    
    // Check if email exist or not exist connect db
    public boolean isEmailExist(String email) {
        String sql = "SELECT COUNT(*) FROM dbo.Users WHERE email = ?"; 
        
        if (email == null || email.trim().isEmpty()) {
            return false;
        } 
        
        try (Connection conn = new DBContext().getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)){
            
            ps.setString(1, email.trim());
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    // Check plate number exist or not connect db
    public boolean isPlateNumberExist(String plateNumber) {
        String sql = "SELECT COUNT(*) FROM dbo.Vehicles WHERE plate_number = ?";
        
        if (plateNumber == null || plateNumber.trim().isEmpty()) {
            return false;
        }
        
        try (Connection conn = new DBContext().getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)){
            
           ps.setString(1, plateNumber.trim());
           
           try (ResultSet rs = ps.executeQuery()) {
               if (rs.next()) {
                   return rs.getInt(1) > 0;
               }
           }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean registerCustomer(RegisterDTO dto) {
        int memberTierId = 0;
        int userId = 0;
        int customerId = 0;
        
        String getMemberTierSql = "SELECT tier_id FROM dbo.LoyaltyTiers WHERE tier_name = 'Member'";
        
        String insertUserSql = 
                "INSERT INTO dbo.Users "
                + "(email, password_hash, full_name, phone, role, status) "
                + "OUTPUT INSERTED.user_id "
                + "VALUES (?, ?, ?, ?, 'CUSTOMER', 'ACTIVE')";
        
        String insertCustomerSql = 
                "INSERT INTO dbo.Customers " 
                + "(user_id, total_spent, total_washes) "
                + "OUTPUT INSERTED.customer_id "
                + "VALUES (?, 0, 0)";
        
        String insertVehicleSql = 
                "INSERT INTO dbo.Vehicles "
                + "(customer_id, plate_number, brand, model, color, status) "
                + "VALUES (?, ?, ?, ?, ?, 'ACTIVE')";
        
        String insertLoyaltySql = 
                "INSERT INTO dbo.LoyaltyAccounts " 
                + "(customer_id, tier_id, current_points, lifetime_points, last_review_date) "
                + "VALUES (?, ?, 0, 0, GETDATE())";
        
        try(Connection conn = new DBContext().getConnection()) {
            conn.setAutoCommit(false);
            
            try {
                // Tier Member
                try (PreparedStatement ps = conn.prepareStatement(getMemberTierSql);
                        ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        memberTierId = rs.getInt("tier_id");
                    } else {
                        throw new Exception("Member tier not found!");
                    }
                }
                
                // User 
                try (PreparedStatement ps = conn.prepareStatement(insertUserSql)) {
                    ps.setString(1, dto.getEmail().trim());
                    ps.setString(2, dto.getPassword().trim());
                    ps.setString(3, dto.getFullName());
                    ps.setString(4, dto.getPhone().trim());

                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            userId = rs.getInt("user_id");
                        } else {
                            throw new Exception("Cannot create user.");
                        }
                    }
                }
                
                // Customer
                try (PreparedStatement ps = conn.prepareStatement(insertCustomerSql)) {
                    ps.setInt(1, userId);

                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            customerId = rs.getInt("customer_id");
                        } else {
                            throw new Exception("Cannot create customer.");
                        }
                    }
                }
                
                // Vehicle
                try (PreparedStatement ps = conn.prepareStatement(insertVehicleSql)) {
                    ps.setInt(1, customerId);
                    ps.setString(2, dto.getPlateNumber().trim());
                    ps.setString(3, dto.getBrand().trim());
                    ps.setString(4, dto.getModel().trim());
                    ps.setString(5, dto.getColor().trim());

                    int rows = ps.executeUpdate();
                    
                    if (rows == 0) { throw new Exception("Cannot create vehicle."); }
                }
                
                // Loyal Account create
                try (PreparedStatement ps = conn.prepareStatement(insertLoyaltySql)) {
                    ps.setInt(1, customerId);
                    ps.setInt(2, memberTierId);
                
                    int rows = ps.executeUpdate();
                
                    if (rows == 0) { throw new Exception("Cannot create loyalty account."); }
                }
            
                conn.commit();
                return true;
            } catch (Exception e) {
                conn.rollback();
                e.printStackTrace();
                return false;
            }
            
            
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

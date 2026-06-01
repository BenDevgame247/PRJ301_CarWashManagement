
package dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProfileDTO {
    
    // Information    
    private int userId;
    private int customerId;
    private String fullName;
    private String email;
    private String phone;
    private String nickName;
    
    // Performance Overview
    private int totalWashes;
    private BigDecimal monthlyPaid;
    private String tierRank;
    private String nextTierRank;
    private int washesToNextTier;
    private BigDecimal spentToNextTier;
    private double customerRating;
    
    // Recent Activity
    private List<RecentActivityDTO> recentActivities;

    public ProfileDTO() {
        
    }
    
    public ProfileDTO(int userId, int customerId, String fullName, String email, String phone, String nickName, int totalWashes, BigDecimal monthlyPaid, String tierRank, double customerRating, String nextTierRank, int washesToNextTier, BigDecimal spentToNextTier) {
        this.userId = userId;
        this.customerId = customerId;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.nickName = nickName;
        this.totalWashes = totalWashes;
        this.monthlyPaid = monthlyPaid;
        this.tierRank = tierRank;
        this.nextTierRank = nextTierRank;
        this.washesToNextTier = washesToNextTier;
        this.spentToNextTier = spentToNextTier;
        this.customerRating = customerRating;
        recentActivities = new ArrayList<>();
    }
    
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    
    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }
    
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    
    public String getNickName() { return nickName; }
    public void setNickName(String nickName) { this.nickName = nickName; }
    
    public int getTotalWashes() { return totalWashes; }
    public void setTotalWashes(int totalWashes) { this.totalWashes = totalWashes; }
    
    public BigDecimal getMonthlyPaid() { return monthlyPaid; }
    public void setMonthlyPaid(BigDecimal monthlyPaid) { this.monthlyPaid = monthlyPaid; }
    
    public String getTierRank() { return tierRank; }
    public void setTierRank(String tierRank) { this.tierRank = tierRank; }
    
    public double getCustomerRating() { return customerRating; }
    public void setCustomerRating(double customerRating) { this.customerRating = customerRating; }
    
    public List<RecentActivityDTO> getRecentActivity() { return recentActivities; }
    public void setRecentActivity(List<RecentActivityDTO> recentActivities) { this.recentActivities = recentActivities; }
    
    public String getNextTierRank() { return nextTierRank; }
    public void setNextTierRank(String nextTierRank) { this.nextTierRank = nextTierRank; }
    
    public int getWashesToNextTier() { return washesToNextTier; }
    public void setWashesToNextTier(int washesToNextTier) { this.washesToNextTier = washesToNextTier; }
    
    public BigDecimal getSpentToNextTier() { return spentToNextTier; }
    public void setSpentToNextTier(BigDecimal spentToNextTier) { this.spentToNextTier = spentToNextTier; }
    
}

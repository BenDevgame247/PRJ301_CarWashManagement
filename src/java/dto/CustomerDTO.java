/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author MY PC
 */
public class CustomerDTO {
    
    private int customerId;
    private int userId;
    private double totalSpent;
    private int totalWashes;
    
    public CustomerDTO() {
        
    }
    
    public CustomerDTO(int customerId, int userId, double totalSpent, int totalWashes) {
        this.customerId = customerId;
        this.userId = userId;
        this.totalSpent = totalSpent;
        this.totalWashes = totalWashes;
    }
    
    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }
    
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    
    public double getTotalSpent() { return totalSpent; }
    public void setTotalSpent(double totalSpent) { this.totalSpent = totalSpent; }
    
    public int getTotalWashes() { return totalWashes; }
    public void setTotalWashes(int totalWashes) { this.totalWashes = totalWashes; }
    
}

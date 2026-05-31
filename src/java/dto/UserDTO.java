/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author MY PC
 */
public class UserDTO {
    
    private int userId;
    private String email;
    private String fullName;
    private String phone;
    private String role;
    private String status;
    
    public UserDTO() {
        
    }
    
    public UserDTO(int userId, String email, String fullName, String phone, String role, String status) {
        this.userId = userId;
        this.email = email;
        this.fullName = fullName;
        this.phone = phone;
        this.role = role;
        this.status = status;
    }
    
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}


package dto;

public class RegisterDTO {
    
    private String fullName;
    private String email;
    private String password;
    private String phone;
    private String plateNumber;
    private String brand;
    private String model;
    private String color;
    
    public RegisterDTO() {
        
    }
    
    public RegisterDTO(String fullName, String email, String password, String phone, String plateNumber, String brand, String model, String color) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.plateNumber = plateNumber;
        this.brand = brand;
        this.model = model;
        this.color = color;
    }
    
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    
    public String getPlateNumber() { return plateNumber; }
    public void setPlateNumber(String plateNumber) { this.plateNumber = plateNumber; }
    
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
}

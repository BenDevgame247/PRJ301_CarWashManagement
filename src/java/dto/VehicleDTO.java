/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author MY PC
 */
public class VehicleDTO {
    
    private int vehicleId;
    private int customerId;
    private String plateNumber;
    private String brand;
    private String model;
    private String color;
    private String status;
    
    public VehicleDTO() {
    
    }
    
    public VehicleDTO(int vehicleId, int customerId, String plateNumber, String brand, String model, String color, String status) {
        this.vehicleId = vehicleId;
        this.customerId = customerId;
        this.plateNumber = plateNumber;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.status = status;
    }
    
    public int getVehicleId() { return vehicleId; }
    public void setVehicleId(int vehicleId) { this.vehicleId = vehicleId; }
    
    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }
    
    public String getPlateNumber() { return plateNumber; }
    public void setPlateNumber(String plateNumber) { this.plateNumber = plateNumber; }
    
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

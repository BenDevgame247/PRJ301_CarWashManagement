
package dto;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

public class RecentActivityDTO {
    
    private int bookingId;
    private String serviceName;
    private String vehicleName;
    private String plateNumber;
    private String status;
    private BigDecimal finalAmount;
    private Date bookingDate;
    private Time bookingTime;
    
    public RecentActivityDTO() {
        
    }
    
    public RecentActivityDTO(int bookingId, String serviceName, String vehicleName, String plateNumber, String status, BigDecimal finalAmount, Date bookingDate, Time bookingTime) {
        this.bookingId = bookingId;
        this.serviceName = serviceName;
        this.vehicleName = vehicleName;
        this.plateNumber = plateNumber;
        this.status = status;
        this.finalAmount = finalAmount;
        this.bookingDate = bookingDate;
        this.bookingTime = bookingTime;
    }
    
    public int getBookingId() { return bookingId; }
    public void setBookingId(int bookingId) { this.bookingId = bookingId; }
    
    public String getServiceName() { return serviceName; }
    public void setServiceName(String serviceName) { this.serviceName = serviceName; }
    
    public String getVehicleName() { return vehicleName; }
    public void setVehicleName(String vehicleName) { this.vehicleName = vehicleName; }
    
    public String getPlateNumber() { return plateNumber; }
    public void setPlateNumber(String plateNumber) { this.plateNumber = plateNumber; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public BigDecimal getFinalAmount() { return finalAmount; }
    public void setFinalAmount(BigDecimal finalAmount) { this.finalAmount = finalAmount; }
    
    public Date getBookingDate() { return bookingDate; }
    public void setBookingDate(Date bookingDate) { this.bookingDate = bookingDate; }
    
    public Time getBookingTime() { return bookingTime; }
    public void setBookingTime(Time bookingTime) { this.bookingTime = bookingTime; }
}

package dto;

import java.sql.Date;
import java.sql.Time;

/**
 * Dữ liệu khách hàng gửi lên khi đặt lịch (chưa lưu DB).
 * Servlet sẽ đọc request.getParameter(...) rồi đóng gói vào DTO này
 * trước khi đưa qua BookingService xử lý logic.
 */
public class BookingRequestDTO {

    private int customerId;
    private int vehicleId;
    private int serviceId;
    private Integer promotionId; // có thể null nếu không áp dụng promotion
    private Date bookingDate;
    private Time bookingTime;
    private String note;

    public BookingRequestDTO() {
    }

    public BookingRequestDTO(int customerId, int vehicleId, int serviceId,
            Integer promotionId, Date bookingDate, Time bookingTime, String note) {
        this.customerId = customerId;
        this.vehicleId = vehicleId;
        this.serviceId = serviceId;
        this.promotionId = promotionId;
        this.bookingDate = bookingDate;
        this.bookingTime = bookingTime;
        this.note = note;
    }

    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public int getVehicleId() { return vehicleId; }
    public void setVehicleId(int vehicleId) { this.vehicleId = vehicleId; }

    public int getServiceId() { return serviceId; }
    public void setServiceId(int serviceId) { this.serviceId = serviceId; }

    public Integer getPromotionId() { return promotionId; }
    public void setPromotionId(Integer promotionId) { this.promotionId = promotionId; }

    public Date getBookingDate() { return bookingDate; }
    public void setBookingDate(Date bookingDate) { this.bookingDate = bookingDate; }

    public Time getBookingTime() { return bookingTime; }
    public void setBookingTime(Time bookingTime) { this.bookingTime = bookingTime; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
}

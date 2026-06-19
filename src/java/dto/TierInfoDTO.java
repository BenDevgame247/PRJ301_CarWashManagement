package dto;

/**
 * Thông tin loyalty tier cần để kiểm tra điều kiện booking.
 * Đây là DTO "gọn", chỉ chứa đúng field bạn cần cho logic canBook,
 * khác với ProfileDTO (đầy đủ hơn, dùng để hiển thị UI).
 */
public class TierInfoDTO {

    private int tierId;
    private String tierName;
    private int bookingDaysAhead; // số ngày tối đa được đặt trước

    public TierInfoDTO() {
    }

    public TierInfoDTO(int tierId, String tierName, int bookingDaysAhead) {
        this.tierId = tierId;
        this.tierName = tierName;
        this.bookingDaysAhead = bookingDaysAhead;
    }

    public int getTierId() { return tierId; }
    public void setTierId(int tierId) { this.tierId = tierId; }

    public String getTierName() { return tierName; }
    public void setTierName(String tierName) { this.tierName = tierName; }

    public int getBookingDaysAhead() { return bookingDaysAhead; }
    public void setBookingDaysAhead(int bookingDaysAhead) { this.bookingDaysAhead = bookingDaysAhead; }
}

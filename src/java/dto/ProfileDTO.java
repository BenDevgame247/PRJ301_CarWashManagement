package dto;

public class ProfileDTO {

    private int userId;

    private int customerId;
    private String fullName;
    private String email;
    private String phone;

    private int vehicleId;
    private String brand;
    private String model;
    private String color;
    private String plateNumber;

    private int tierId;
    private String tierName;
    private int currentPoints;
    private int lifetimePoints;

    private int rewardId;
    private String rewardName;
    private int requiredPoints;
    private int pointsToNextReward;

    public ProfileDTO() {

    }

    public ProfileDTO(
            int userId, int customerId, String fullName, String email, String phone,
            int vehicleId, String brand, String model, String color, String plateNumber,
            int tierId, String tierName, int currentPoints, int lifetimePoints,
            int rewardId, String rewardName, int requiredPoints, int pointsToNextReward
    ) {
        this.userId = userId;
        this.customerId = customerId;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.vehicleId = vehicleId;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.plateNumber = plateNumber;
        this.tierId = tierId;
        this.tierName = tierName;
        this.currentPoints = currentPoints;
        this.lifetimePoints = lifetimePoints;
        this.rewardId = rewardId;
        this.rewardName = rewardName;
        this.requiredPoints = requiredPoints;
        this.pointsToNextReward = pointsToNextReward;
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

    public int getVehicleId() { return vehicleId; }
    public void setVehicleId(int vehicleId) { this.vehicleId = vehicleId; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public String getPlateNumber() { return plateNumber; }
    public void setPlateNumber(String plateNumber) { this.plateNumber = plateNumber; }

    public int getTierId() { return tierId; }
    public void setTierId(int tierId) { this.tierId = tierId; }

    public String getTierName() { return tierName; }
    public void setTierName(String tierName) { this.tierName = tierName; }

    public int getCurrentPoints() { return currentPoints; }
    public void setCurrentPoints(int currentPoints) { this.currentPoints = currentPoints; }

    public int getLifetimePoints() { return lifetimePoints; }
    public void setLifetimePoints(int lifetimePoints) { this.lifetimePoints = lifetimePoints; }

    public int getRewardId() { return rewardId; }
    public void setRewardId(int rewardId) { this.rewardId = rewardId; }

    public String getRewardName() { return rewardName; }
    public void setRewardName(String rewardName) { this.rewardName = rewardName; }

    public int getRequiredPoints() { return requiredPoints; }
    public void setRequiredPoints(int requiredPoints) { this.requiredPoints = requiredPoints; }

    public int getPointsToNextReward() { return pointsToNextReward; }
    public void setPointsToNextReward(int pointsToNextReward) { this.pointsToNextReward = pointsToNextReward; }
}

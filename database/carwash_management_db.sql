CREATE database PRJ301_CarWashManagement
GO

USE PRJ301_CarWashManagement
GO

CREATE TABLE Users(
	user_id			INT IDENTITY(1,1)	PRIMARY KEY,
	email			VARCHAR(100)		NOT NULL UNIQUE,
	password_hash           VARCHAR(255)		NOT NULL,
	full_name		NVARCHAR(100)		NOT NULL,
	phone			VARCHAR(20)		NOT NULL,
	role			VARCHAR(20)		NOT NULL DEFAULT 'CUSTOMER',
	status			VARCHAR(20)		NOT NULL DEFAULT 'ACTIVE',
	created_at		DATETIME		NOT NULL DEFAULT GETDATE()
);

CREATE TABLE Customers(
	customer_id		INT IDENTITY(1,1)	PRIMARY KEY,
	user_id			INT			NOT NULL UNIQUE,		
	total_spent		DECIMAL(18,2)		NOT NULL DEFAULT 0,
	total_washes            INT			NOT NULL DEFAULT 0,
	created_at		DATETIME		NOT NULL DEFAULT GETDATE(),
	
	CONSTRAINT FK_Customers_Users FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

CREATE TABLE Vehicles(
	vehicle_id		INT IDENTITY(1,1)	PRIMARY KEY,
	customer_id		INT			NOT NULL,
	plate_number            VARCHAR(20)		NOT NULL UNIQUE,
	brand			NVARCHAR(50),
	model			NVARCHAR(50),
	color			NVARCHAR(30),
	status			VARCHAR(20)		NOT NULL DEFAULT 'ACTIVE',

	CONSTRAINT FK_Vehicles_Customers FOREIGN KEY (customer_id) REFERENCES Customers(customer_id)
);

CREATE TABLE LoyaltyTiers(
	tier_id				INT IDENTITY(1,1)	PRIMARY KEY,
	tier_name			VARCHAR(50)		NOT NULL UNIQUE,
	min_washes			INT			NOT NULL,
	min_spent			DECIMAL(18,2)		NOT NULL,
	point_rate			DECIMAL(5,2)		NOT NULL,
	discount_percent                DECIMAL(5,2)		NOT NULL,
	booking_days_ahead              INT			NOT NULL,
	description			NVARCHAR(255)
);

CREATE TABLE LoyaltyAccounts(
	loyalty_id			INT IDENTITY(1,1)	PRIMARY KEY,
	customer_id			INT			NOT NULL UNIQUE,
	tier_id				INT			NOT NULL,
	current_points                  INT			NOT NULL DEFAULT 0,
	lifetime_points                 INT			NOT NULL DEFAULT 0,
	last_review_date                DATE,
	updated_at			DATETIME		NOT NULL DEFAULT GETDATE()

	CONSTRAINT FK_LoyaltyAccounts_Customers FOREIGN KEY (customer_id) REFERENCES Customers(customer_id),
	CONSTRAINT FK_LoyaltyAccounts_LoyaltyTiers FOREIGN KEY (tier_id) REFERENCES LoyaltyTiers(tier_id)
);

CREATE TABLE Rewards(
	reward_id			INT IDENTITY(1,1)	PRIMARY KEY,
	reward_name			NVARCHAR(100)		NOT NULL,
	required_points                 INT			NOT NULL,
	discount_amount                 DECIMAL(18,2),
	reward_type			VARCHAR(30)		NOT NULL,
	description			NVARCHAR(255),
	status				VARCHAR(20)		NOT NULL DEFAULT 'ACTIVE'
);

CREATE TABLE ServicePackages(
	service_id			INT IDENTITY(1,1)	PRIMARY KEY,
	service_name                    NVARCHAR(100)		NOT NULL,
	description			NVARCHAR(255),
	price				DECIMAL(18,2)		NOT NULL,
	duration_minutes                INT			NOT NULL,
	status				VARCHAR(20)		NOT NULL DEFAULT 'ACTIVE'
);

CREATE TABLE Promotions(
	promotion_id                    INT IDENTITY(1,1)	PRIMARY KEY,
	target_tier_id			INT,
	promotion_name                  NVARCHAR(100)		NOT NULL,
	description			NVARCHAR(255),
	discount_percent                DECIMAL(5,2)		NOT NULL DEFAULT 0,
	discount_amount                 DECIMAL(18,2)		NOT NULL DEFAULT 0,
	start_date			DATE			NOT NULL,
	end_date			DATE			NOT NULL,
	status				VARCHAR(20)		NOT NULL DEFAULT 'ACTIVE'

	CONSTRAINT FK_Promotions_LoyaltyTiers FOREIGN KEY (target_tier_id) REFERENCES LoyaltyTiers(tier_id)
);

CREATE TABLE Bookings(
	booking_id		INT IDENTITY(1,1)	PRIMARY KEY,
	customer_id		INT			NOT NULL,
	vehicle_id		INT			NOT NULL,
	service_id		INT			NOT NULL,
	promotion_id            INT,
	booking_date            DATE			NOT NULL,
	booking_time            TIME			NOT NULL,
	status			VARCHAR(20)		NOT NULL DEFAULT 'PENDING',
	original_price          DECIMAL(18,2)		NOT NULL,
	discount_amount         DECIMAL(18,2)		NOT NULL DEFAULT 0,
	final_amount            DECIMAL(18,2)		NOT NULL,
	note			NVARCHAR(255),
	create_at		DATETIME                NOT NULL DEFAULT GETDATE(),

	CONSTRAINT FK_Bookings_Customer FOREIGN KEY (customer_id) REFERENCES Customers(customer_id),
	CONSTRAINT FK_Bookings_Vehicles FOREIGN KEY (vehicle_id) REFERENCES Vehicles(vehicle_id),
	CONSTRAINT FK_Bookings_ServicePackages FOREIGN KEY (service_id) REFERENCES ServicePackages(service_id),
	CONSTRAINT FK_Bookings_Promotions FOREIGN KEY (promotion_id) REFERENCES Promotions(promotion_id)
);

CREATE TABLE PointTransactions(
	transaction_id		INT IDENTITY(1,1)	PRIMARY KEY,
	loyalty_id              INT			NOT NULL,
	booking_id		INT,
	points			INT                     NOT NULL,
	transaction_type	VARCHAR(20)		NOT NULL,
	description		NVARCHAR(255),
	created_at		DATETIME		NOT NULL DEFAULT GETDATE(),
	expired_at		DATE,

	CONSTRAINT FK_PointTransactions_LoyaltyAccounts FOREIGN KEY (loyalty_id) REFERENCES LoyaltyAccounts(loyalty_id),
	CONSTRAINT FK_PointTransactions_Bookings FOREIGN KEY (booking_id) REFERENCES Bookings(booking_id)
);

CREATE TABLE RewardRedemptions(
	redemption_id       INT IDENTITY(1,1)	PRIMARY KEY,
	customer_id         INT			NOT NULL,
	reward_id           INT			NOT NULL,
	booking_id          INT,
	points_used         INT			NOT NULL,
	redeemed_at         DATETIME		NOT NULL DEFAULT GETDATE(),
	status              VARCHAR(20)		NOT NULL DEFAULT 'USED',

	CONSTRAINT FK_RewardRedemptions_Customers FOREIGN KEY (customer_id) REFERENCES Customers(customer_id),
	CONSTRAINT FK_RewardRedemptions_Rewards FOREIGN KEY (reward_id) REFERENCES Rewards(reward_id),
	CONSTRAINT FK_RewardRedemption_Bookings FOREIGN KEY (booking_id) REFERENCES Bookings(booking_id)
);

CREATE database PRJ301_CarWashManagement
GO

USE PRJ301_CarWashManagement
GO

CREATE TABLE Users(
	user_id			INT IDENTITY(1,1)	PRIMARY KEY,
    full_name		NVARCHAR(100)		NOT NULL,
	phone           VARCHAR(20)         NOT NULL,
    email           VARCHAR(100)        NOT NULL UNIQUE,
    password_hash   VARCHAR(255)        NOT NULL,
    address         NVARCHAR(255)       NULL,
    nick_name       NVARCHAR(50)        NULL,
    role            VARCHAR(20)         NOT NULL DEFAULT 'CUSTOMER',
    created_at      DATETIME            NOT NULL DEFAULT GETDATE(),
    status          VARCHAR(20)         NOT NULL DEFAULT 'ACTIVE'
);

CREATE TABLE Customers(
	customer_id		INT IDENTITY(1,1)	PRIMARY KEY,
	user_id			INT					NOT NULL UNIQUE,		
	total_spent		DECIMAL(18,2)		NOT NULL DEFAULT 0,
	total_washes    INT					NOT NULL DEFAULT 0,
	created_at		DATETIME			NOT NULL DEFAULT GETDATE(),
	
	CONSTRAINT FK_Customers_Users FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

CREATE TABLE Vehicles(
	vehicle_id		INT IDENTITY(1,1)	PRIMARY KEY,
	customer_id		INT					NOT NULL,
	plate_number    VARCHAR(20)			NOT NULL UNIQUE,
	brand			NVARCHAR(50),
	model			NVARCHAR(50),
	color			NVARCHAR(30),
	status			VARCHAR(20)			NOT NULL DEFAULT 'ACTIVE',

	CONSTRAINT FK_Vehicles_Customers FOREIGN KEY (customer_id) REFERENCES Customers(customer_id)
);

CREATE TABLE LoyaltyTiers(
	tier_id				INT IDENTITY(1,1)	PRIMARY KEY,
	tier_name			VARCHAR(50)			NOT NULL UNIQUE,
	min_washes			INT					NOT NULL,
	min_spent			DECIMAL(18,2)		NOT NULL,
	point_rate			DECIMAL(5,2)		NOT NULL,
	discount_percent    DECIMAL(5,2)		NOT NULL,
	booking_days_ahead  INT					NOT NULL,
	description			NVARCHAR(255)
);

CREATE TABLE LoyaltyAccounts(
	loyalty_id			INT IDENTITY(1,1)	PRIMARY KEY,
	customer_id			INT					NOT NULL UNIQUE,
	tier_id				INT					NOT NULL,
	current_points      INT					NOT NULL DEFAULT 0,
	lifetime_points     INT					NOT NULL DEFAULT 0,
	last_review_date    DATE,
	updated_at			DATETIME			NOT NULL DEFAULT GETDATE()

	CONSTRAINT FK_LoyaltyAccounts_Customers FOREIGN KEY (customer_id) REFERENCES Customers(customer_id),
	CONSTRAINT FK_LoyaltyAccounts_LoyaltyTiers FOREIGN KEY (tier_id) REFERENCES LoyaltyTiers(tier_id)
);

CREATE TABLE Rewards(
	reward_id			INT IDENTITY(1,1)	PRIMARY KEY,
	reward_name			NVARCHAR(100)		NOT NULL,
	required_points     INT					NOT NULL,
	discount_amount     DECIMAL(18,2),
	reward_type			VARCHAR(30)			NOT NULL,
	description			NVARCHAR(255),
	status				VARCHAR(20)			NOT NULL DEFAULT 'ACTIVE'
);

CREATE TABLE ServicePackages(
	service_id			INT IDENTITY(1,1)	PRIMARY KEY,
	service_name        NVARCHAR(100)		NOT NULL,
	description			NVARCHAR(255),
	price				DECIMAL(18,2)		NOT NULL,
	duration_minutes    INT					NOT NULL,
	status				VARCHAR(20)			NOT NULL DEFAULT 'ACTIVE'
);

CREATE TABLE Promotions(
	promotion_id            INT IDENTITY(1,1)	PRIMARY KEY,
	target_tier_id			INT,
	promotion_name          NVARCHAR(100)		NOT NULL,
	description				NVARCHAR(255),
	discount_percent        DECIMAL(5,2)		NOT NULL DEFAULT 0,
	discount_amount         DECIMAL(18,2)		NOT NULL DEFAULT 0,
	start_date				DATE				NOT NULL,
	end_date				DATE				NOT NULL,
	status					VARCHAR(20)			NOT NULL DEFAULT 'ACTIVE'

	CONSTRAINT FK_Promotions_LoyaltyTiers FOREIGN KEY (target_tier_id) REFERENCES LoyaltyTiers(tier_id)
);

CREATE TABLE Bookings(
	booking_id				INT IDENTITY(1,1)	PRIMARY KEY,
	customer_id				INT					NOT NULL,
	vehicle_id				INT					NOT NULL,
	service_id				INT					NOT NULL,
	promotion_id            INT,
	booking_date            DATE				NOT NULL,
	booking_time            TIME				NOT NULL,
	status					VARCHAR(20)			NOT NULL DEFAULT 'PENDING',
	original_price          DECIMAL(18,2)		NOT NULL,
	discount_amount         DECIMAL(18,2)		NOT NULL DEFAULT 0,
	final_amount            DECIMAL(18,2)		NOT NULL,
	note					NVARCHAR(255),
	create_at				DATETIME            NOT NULL DEFAULT GETDATE(),

	CONSTRAINT FK_Bookings_Customer FOREIGN KEY (customer_id) REFERENCES Customers(customer_id),
	CONSTRAINT FK_Bookings_Vehicles FOREIGN KEY (vehicle_id) REFERENCES Vehicles(vehicle_id),
	CONSTRAINT FK_Bookings_ServicePackages FOREIGN KEY (service_id) REFERENCES ServicePackages(service_id),
	CONSTRAINT FK_Bookings_Promotions FOREIGN KEY (promotion_id) REFERENCES Promotions(promotion_id)
);

CREATE TABLE PointTransactions(
	transaction_id		INT IDENTITY(1,1)	PRIMARY KEY,
	loyalty_id          INT					NOT NULL,
	booking_id			INT,
	points				INT                 NOT NULL,
	transaction_type	VARCHAR(20)			NOT NULL,
	description			NVARCHAR(255),
	created_at			DATETIME			NOT NULL DEFAULT GETDATE(),
	expired_at			DATE,

	CONSTRAINT FK_PointTransactions_LoyaltyAccounts FOREIGN KEY (loyalty_id) REFERENCES LoyaltyAccounts(loyalty_id),
	CONSTRAINT FK_PointTransactions_Bookings FOREIGN KEY (booking_id) REFERENCES Bookings(booking_id)
);

CREATE TABLE RewardRedemptions(
	redemption_id       INT IDENTITY(1,1)	PRIMARY KEY,
	customer_id         INT					NOT NULL,
	reward_id           INT					NOT NULL,
	booking_id          INT,
	points_used         INT					NOT NULL,
	redeemed_at         DATETIME			NOT NULL DEFAULT GETDATE(),
	status              VARCHAR(20)			NOT NULL DEFAULT 'USED',

	CONSTRAINT FK_RewardRedemptions_Customers FOREIGN KEY (customer_id) REFERENCES Customers(customer_id),
	CONSTRAINT FK_RewardRedemptions_Rewards FOREIGN KEY (reward_id) REFERENCES Rewards(reward_id),
	CONSTRAINT FK_RewardRedemption_Bookings FOREIGN KEY (booking_id) REFERENCES Bookings(booking_id)
);

INSERT INTO Users (email, password_hash, full_name, phone, role, status, address, nick_name)
VALUES
('admin@admin.com', 'admin', N'Admin System', '0900000000', 'ADMIN', 'ACTIVE', N'Ho Chi Minh', 'Admin')

INSERT INTO LoyaltyTiers (tier_name, min_washes, min_spent, point_rate, discount_percent, booking_days_ahead, description)
VALUES
('Member', 0, 0, 1.00, 0.00, 7, N'Basic member'),
('Bronze', 1, 500000, 1.00, 0.00, 7, N'Bronze member'),
('Silver', 5, 1500000, 1.10, 2.00, 10, N'Silver member'),
('Gold', 10, 3000000, 1.20, 5.00, 14, N'Gold member'),
('Diamond', 20, 5000000, 1.50, 8.00, 21, N'Diamond member'),
('Platinum', 35, 15000000, 1.80, 10.00, 30, N'Platinum member'),
('VIP', 50, 25000000, 2.00, 15.00, 30, N'VIP member');

-- ================================================
-- 1. USERS (4 customer + 1 staff)
-- Password: 123456
-- ================================================
INSERT INTO Users (full_name, phone, email, password_hash, address, nick_name, role, status)
VALUES
('Nguyen Van An',    '0901111111', 'an@gmail.com',      '123456', 'District 1, HCMC',    'An',    'CUSTOMER', 'ACTIVE'),
('Tran Thi Bich',    '0902222222', 'bich@gmail.com',    '123456', 'District 3, HCMC',    'Bich',  'CUSTOMER', 'ACTIVE'),
('Le Van Cuong',     '0903333333', 'cuong@gmail.com',   '123456', 'Binh Thanh, HCMC',    'Cuong', 'CUSTOMER', 'ACTIVE'),
('Pham Thi Dung',    '0904444444', 'dung@gmail.com',    '123456', 'Thu Duc, HCMC',        'Dung',  'CUSTOMER', 'ACTIVE'),
('Nguyen Van Staff', '0905555555', 'staff@carwash.com', '123456', 'District 5, HCMC',    'Staff', 'STAFF',    'ACTIVE');
GO

-- ================================================
-- 2. CUSTOMERS
-- ================================================
INSERT INTO Customers (user_id, total_spent, total_washes)
VALUES
(2, 3200000, 10),
(3, 800000,  3),
(4, 6000000, 22),
(5, 0,       0);
GO

-- ================================================
-- 3. VEHICLES
-- ================================================
INSERT INTO Vehicles (customer_id, plate_number, brand, model, color, status)
VALUES
(1, '51A-12345', 'Toyota',  'Vios',    'White',  'ACTIVE'),
(1, '51B-67890', 'Honda',   'City',    'Black',  'ACTIVE'),
(2, '51C-11111', 'Mazda',   'CX-5',    'Red',    'ACTIVE'),
(3, '51D-22222', 'Ford',    'Ranger',  'Gray',   'ACTIVE'),
(3, '51E-33333', 'Kia',     'Morning', 'Blue',   'ACTIVE'),
(4, '51F-44444', 'Hyundai', 'Accent',  'Silver', 'ACTIVE');
GO

-- ================================================
-- 4. SERVICE PACKAGES
-- ================================================
INSERT INTO ServicePackages (service_name, description, price, duration_minutes, status)
VALUES
('Basic Wash',    'Basic exterior car wash',                        50000,  30,  'ACTIVE'),
('Premium Wash',  'Car wash + interior vacuuming',                  120000, 60,  'ACTIVE'),
('Full Detail',   'Full interior and exterior cleaning + polishing',250000, 120, 'ACTIVE'),
('Express Wash',  '15-minute quick wash',                            35000,  15,  'ACTIVE'),
('Ceramic Coat',  'Ceramic coating for paint protection',           500000, 180, 'ACTIVE'),
('Interior Deep', 'Deep interior cleaning + odor removal',          180000, 90,  'ACTIVE');
GO

-- ================================================
-- 5. REWARDS
-- ================================================
INSERT INTO Rewards (reward_name, required_points, discount_amount, reward_type, description, status)
VALUES
('Discount 20k', 100, 20000,  'DISCOUNT',  'Get 20,000 VND off next wash', 'ACTIVE'),
('Discount 50k', 250, 50000,  'DISCOUNT',  'Get 50,000 VND off next wash', 'ACTIVE'),
('Free Wash',    500, 120000, 'FREE_WASH', '1 free Premium wash',           'ACTIVE'),
('Discount 100k',450, 100000, 'DISCOUNT',  'Get 100,000 VND off next wash','ACTIVE');
GO

-- ================================================
-- 6. LOYALTY ACCOUNTS
-- tier: 1=Member(7d), 2=Bronze(7d), 3=Silver(10d), 4=Gold(14d), 5=Diamond(21d)
-- ================================================
INSERT INTO LoyaltyAccounts (customer_id, tier_id, current_points, lifetime_points, last_review_date, updated_at)
VALUES
(1, 4, 320,  640,  GETDATE(), GETDATE()),
(2, 2, 80,   160,  GETDATE(), GETDATE()),
(3, 5, 900,  1800, GETDATE(), GETDATE()),
(4, 1, 0,    0,    GETDATE(), GETDATE());
GO

-- ================================================
-- 7. PROMOTIONS
-- ================================================
INSERT INTO Promotions (target_tier_id, promotion_name, description, discount_percent, discount_amount, start_date, end_date, status)
VALUES
(4, 'Gold Summer Deal',  'Summer promotion for Gold members',   10.00, 0,     '2026-06-01', '2026-08-31', 'ACTIVE'),
(5, 'Diamond VIP Offer', 'Special offer for Diamond members',   15.00, 0,     '2026-06-01', '2026-12-31', 'ACTIVE'),
(1, 'Welcome Newbie',    'Welcome discount for new members',     0.00,  20000, '2026-01-01', '2026-12-31', 'ACTIVE');
GO

-- ================================================
-- 8. BOOKINGS
-- ================================================
INSERT INTO Bookings (customer_id, vehicle_id, service_id, promotion_id, booking_date, booking_time, status, original_price, discount_amount, final_amount, note)
VALUES
(1, 1, 2, NULL, '2026-06-10', '09:00:00', 'COMPLETED', 120000, 0,     120000, 'Regular wash'),
(1, 2, 3, 1,    '2026-06-15', '14:00:00', 'COMPLETED', 250000, 25000, 225000, 'Gold promotion applied'),
(2, 3, 1, NULL, '2026-06-12', '10:30:00', 'COMPLETED', 50000,  0,     50000,  ''),
(3, 4, 3, 2,    '2026-06-18', '08:00:00', 'COMPLETED', 250000, 37500, 212500, 'Diamond discount applied'),
(1, 1, 1, NULL, DATEADD(DAY, 3, GETDATE()), '11:00:00', 'PENDING', 50000, 0,  50000, 'Upcoming booking');
GO

-- ================================================
-- 9. POINT TRANSACTIONS
-- ================================================
INSERT INTO PointTransactions (loyalty_id, booking_id, points, transaction_type, description, created_at)
VALUES
(1, 1, 120, 'EARN', 'Points earned from booking #1', '2026-06-10 09:30:00'),
(1, 2, 270, 'EARN', 'Points earned from booking #2', '2026-06-15 15:00:00'),
(2, 3, 50,  'EARN', 'Points earned from booking #3', '2026-06-12 11:00:00'),
(3, 4, 319, 'EARN', 'Points earned from booking #4', '2026-06-18 08:30:00');
GO
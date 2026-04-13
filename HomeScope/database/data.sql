
--users
INSERT INTO users (full_name, email, password_hash) VALUES
('Alice Johnson', 'alice@example.com', 'hash1'),
('Bob Smith', 'bob@example.com', 'hash2'),
('Charlie Brown', 'charlie@example.com', 'hash3'),
('David Lee', 'david@example.com', 'hash4'),
('Eva Green', 'eva@example.com', 'hash5');

--locations
INSERT INTO locations (city, state, zip_code, neighborhood, county) VALUES

-- Georgia
('Athens', 'GA', '30601', 'Downtown', 'Clarke'),
('Athens', 'GA', '30605', 'Five Points', 'Clarke'),
('Atlanta', 'GA', '30301', 'Midtown', 'Fulton'),
('Atlanta', 'GA', '30305', 'Buckhead', 'Fulton'),
('Alpharetta', 'GA', '30004', 'Windward', 'Fulton'),
('Savannah', 'GA', '31401', 'Historic District', 'Chatham'),

-- Texas
('Dallas', 'TX', '75001', 'North Dallas', 'Dallas'),
('Dallas', 'TX', '75201', 'Downtown', 'Dallas'),
('Austin', 'TX', '73301', 'Central Austin', 'Travis'),
('Austin', 'TX', '78704', 'South Congress', 'Travis'),
('Houston', 'TX', '77001', 'Midtown', 'Harris'),

-- California
('San Jose', 'CA', '95101', 'Downtown', 'Santa Clara'),
('San Jose', 'CA', '95112', 'Japantown', 'Santa Clara'),
('Los Angeles', 'CA', '90001', 'South LA', 'Los Angeles'),
('Los Angeles', 'CA', '90049', 'Brentwood', 'Los Angeles'),
('San Francisco', 'CA', '94102', 'SoMa', 'San Francisco'),

-- New York
('New York', 'NY', '10001', 'Manhattan', 'New York'),
('New York', 'NY', '11201', 'Brooklyn Heights', 'Kings'),
('Buffalo', 'NY', '14201', 'Elmwood Village', 'Erie'),

-- Washington
('Seattle', 'WA', '98101', 'Capitol Hill', 'King'),
('Seattle', 'WA', '98109', 'South Lake Union', 'King'),

-- Illinois
('Chicago', 'IL', '60601', 'Loop', 'Cook'),
('Chicago', 'IL', '60614', 'Lincoln Park', 'Cook'),

-- Florida
('Miami', 'FL', '33101', 'Downtown', 'Miami-Dade'),
('Orlando', 'FL', '32801', 'Lake Eola', 'Orange'),
('Tampa', 'FL', '33602', 'Channelside', 'Hillsborough'),

-- North Carolina
('Charlotte', 'NC', '28202', 'Uptown', 'Mecklenburg'),
('Raleigh', 'NC', '27601', 'Downtown', 'Wake'),

-- Colorado
('Denver', 'CO', '80202', 'LoDo', 'Denver'),
('Denver', 'CO', '80203', 'Capitol Hill', 'Denver'),

-- Arizona
('Phoenix', 'AZ', '85001', 'Downtown', 'Maricopa'),
('Scottsdale', 'AZ', '85251', 'Old Town', 'Maricopa'),

-- Massachusetts
('Boston', 'MA', '02108', 'Beacon Hill', 'Suffolk'),
('Boston', 'MA', '02116', 'Back Bay', 'Suffolk'),

-- Tennessee
('Nashville', 'TN', '37201', 'Downtown', 'Davidson'),

-- Nevada
('Las Vegas', 'NV', '89101', 'Downtown', 'Clark');


-- properties
-- IMPORTANT: Adjust max location_id manually if needed
-- (Run: SELECT COUNT(*) FROM locations;)

INSERT INTO properties (street_address, property_type, bedrooms, bathrooms, square_feet, lot_size, year_built, location_id)
SELECT 
    CONCAT('Street ', t1.n + t2.n*10 + t3.n*100, ' Apt ', FLOOR(RAND()*100)),
    'House',
    FLOOR(1 + RAND()*5),
    ROUND(1 + RAND()*3, 1),
    FLOOR(800 + RAND()*4000),
    FLOOR(1000 + RAND()*5000),
    FLOOR(1980 + RAND()*44),
    FLOOR(1 + RAND()*36)
FROM
    (SELECT 0 n UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 
     UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) t1
CROSS JOIN
    (SELECT 0 n UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 
     UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) t2
CROSS JOIN
    (SELECT 0 n UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 
     UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) t3
LIMIT 1200;

--listings

INSERT INTO listings (property_id, list_price, listing_status, date_listed, days_on_market)
SELECT 
    property_id,
    FLOOR(150000 + RAND()*700000),
    'Active',
    DATE_SUB(CURDATE(), INTERVAL FLOOR(RAND()*100) DAY),
    FLOOR(RAND()*100)
FROM properties;




--Price history

INSERT INTO price_history (property_id, recorded_date, price)
SELECT 
    property_id,
    DATE_SUB(CURDATE(), INTERVAL FLOOR(RAND()*50) DAY),
    FLOOR(150000 + RAND()*700000)
FROM properties;


--Saved Properties
INSERT INTO saved_properties (user_id, property_id)
SELECT 
    ((p.property_id % 5) + 1),
    p.property_id
FROM properties p
LIMIT 2000;
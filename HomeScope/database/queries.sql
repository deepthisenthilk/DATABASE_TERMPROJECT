-- User Signup 

INSERT INTO users (full_name, email, password_hash)
VALUES (?, ?, ?);

-- User Login

SELECT user_id, full_name, email, password_hash
FROM users
WHERE email = ?;


-- Search Properties

SELECT 
    l.listing_id,
    p.property_id,
    p.street_address,
    loc.city,
    loc.state,
    loc.zip_code,
    p.bedrooms,
    p.bathrooms,
    p.square_feet,
    l.list_price,
    l.listing_status
FROM listings l
JOIN properties p ON l.property_id = p.property_id
JOIN locations loc ON p.location_id = loc.location_id
WHERE l.listing_status = 'Active'
  AND loc.city = ?
  AND l.list_price BETWEEN ? AND ?
  AND p.bedrooms >= ?
ORDER BY l.list_price ASC;


-- Property Info

SELECT 
    p.property_id,
    p.street_address,
    p.property_type,
    p.bedrooms,
    p.bathrooms,
    p.square_feet,
    p.year_built,
    loc.city,
    loc.state,
    loc.zip_code,
    l.list_price,
    l.listing_status,
    l.days_on_market
FROM properties p
JOIN locations loc ON p.location_id = loc.location_id
JOIN listings l ON p.property_id = l.property_id
WHERE p.property_id = ?;

-- Price History



SELECT 
    recorded_date,
    price
FROM price_history
WHERE property_id = ?
ORDER BY recorded_date DESC;

-- Saved Property


INSERT INTO saved_properties (user_id, property_id)
VALUES (?, ?);


-- Remove a saved property

DELETE FROM saved_properties
WHERE user_id = ? AND property_id = ?;


--Get saved properties

SELECT 
    p.property_id,
    p.street_address,
    loc.city,
    loc.state,
    l.list_price
FROM saved_properties sp
JOIN properties p ON sp.property_id = p.property_id
JOIN locations loc ON p.location_id = loc.location_id
JOIN listings l ON p.property_id = l.property_id
WHERE sp.user_id = ?;

-- Update user name


UPDATE users
SET full_name = ?
WHERE user_id = ?;


-- average price per city

SELECT 
    loc.city,
    COUNT(*) AS total_listings,
    AVG(l.list_price) AS avg_price
FROM listings l
JOIN properties p ON l.property_id = p.property_id
JOIN locations loc ON p.location_id = loc.location_id
WHERE l.listing_status = 'Active'
GROUP BY loc.city
ORDER BY avg_price DESC;


-- price per sqft

SELECT 
    loc.city,
    AVG(l.list_price / p.square_feet) AS avg_price_per_sqft
FROM listings l
JOIN properties p ON l.property_id = p.property_id
JOIN locations loc ON p.location_id = loc.location_id
WHERE p.square_feet > 0
GROUP BY loc.city;


-- underpriced properties check

SELECT 
    p.property_id,
    p.street_address,
    l.list_price
FROM properties p
JOIN listings l ON p.property_id = l.property_id
WHERE l.list_price < (
    SELECT AVG(list_price) FROM listings
);
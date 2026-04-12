
--create database
CREATE DATABASE IF NOT EXISTS homescope_db;
USE homescope_db;

--users table
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

--locations table
CREATE TABLE locations (
    location_id INT AUTO_INCREMENT PRIMARY KEY,
    city VARCHAR(100) NOT NULL,
    state VARCHAR(50) NOT NULL,
    zip_code VARCHAR(10) NOT NULL,
    neighborhood VARCHAR(100),
    county VARCHAR(100)
);

--properties table
CREATE TABLE properties (
    property_id INT AUTO_INCREMENT PRIMARY KEY,
    street_address VARCHAR(255) NOT NULL,
    property_type VARCHAR(50),
    bedrooms INT,
    bathrooms DECIMAL(3,1),
    square_feet INT,
    lot_size INT,
    year_built INT,
    location_id INT NOT NULL,
    FOREIGN KEY (location_id) REFERENCES locations(location_id)
        ON DELETE CASCADE
);

--listing table
CREATE TABLE listings (
    listing_id INT AUTO_INCREMENT PRIMARY KEY,
    property_id INT NOT NULL,
    list_price DECIMAL(12,2) NOT NULL,
    listing_status VARCHAR(50) NOT NULL,
    date_listed DATE,
    date_removed DATE,
    days_on_market INT,
    FOREIGN KEY (property_id) REFERENCES properties(property_id)
        ON DELETE CASCADE
);

--price history table
CREATE TABLE price_history (
    history_id INT AUTO_INCREMENT PRIMARY KEY,
    property_id INT NOT NULL,
    recorded_date DATE NOT NULL,
    price DECIMAL(12,2) NOT NULL,
    FOREIGN KEY (property_id) REFERENCES properties(property_id)
        ON DELETE CASCADE,
    UNIQUE (property_id, recorded_date)
);

--saved properties
CREATE TABLE saved_properties (
    user_id INT NOT NULL,
    property_id INT NOT NULL,
    saved_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id, property_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
        ON DELETE CASCADE,
    FOREIGN KEY (property_id) REFERENCES properties(property_id)
        ON DELETE CASCADE
);



CREATE INDEX idx_locations_city_zip 
ON locations(city, zip_code);

CREATE INDEX idx_listings_status_price 
ON listings(listing_status, list_price);

CREATE INDEX idx_price_history_property_date 
ON price_history(property_id, recorded_date);
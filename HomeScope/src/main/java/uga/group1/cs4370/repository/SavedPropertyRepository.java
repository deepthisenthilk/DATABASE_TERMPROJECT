package uga.group1.cs4370.repository;

import uga.group1.cs4370.model.SavedPropertyView;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SavedPropertyRepository {

    private final JdbcTemplate jdbcTemplate;

    public SavedPropertyRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // SAVE PROPERTY
    public void saveProperty(int userId, int propertyId) {
        String sql = """
                    INSERT INTO saved_properties (user_id, property_id)
                    VALUES (?, ?)
                """;

        jdbcTemplate.update(sql, userId, propertyId);
    }

    // REMOVE PROPERTY
    public void removeProperty(int userId, int propertyId) {
        String sql = """
                    DELETE FROM saved_properties
                    WHERE user_id = ? AND property_id = ?
                """;

        jdbcTemplate.update(sql, userId, propertyId);
    }

    // GET SAVED PROPERTIES
    public List<SavedPropertyView> getSavedProperties(int userId) {

        String sql = """
                    SELECT
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
                    FROM saved_properties sp
                    JOIN properties p ON sp.property_id = p.property_id
                    JOIN locations loc ON p.location_id = loc.location_id
                    JOIN listings l ON p.property_id = l.property_id
                    WHERE sp.user_id = ?
                """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            SavedPropertyView s = new SavedPropertyView();

            s.setPropertyId(rs.getInt("property_id"));
            s.setStreetAddress(rs.getString("street_address"));
            s.setCity(rs.getString("city"));
            s.setState(rs.getString("state"));
            s.setZipCode(rs.getString("zip_code"));
            s.setBedrooms(rs.getInt("bedrooms"));
            s.setBathrooms(rs.getDouble("bathrooms"));
            s.setSquareFeet(rs.getInt("square_feet"));
            s.setListPrice(rs.getDouble("list_price"));
            s.setListingStatus(rs.getString("listing_status"));

            return s;
        }, userId);
    }
}
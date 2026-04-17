package uga.group1.cs4370.repository;

import uga.group1.cs4370.model.PriceHistory;
import uga.group1.cs4370.model.PropertyDetails;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PropertyRepository {

    private final JdbcTemplate jdbcTemplate;

    public PropertyRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // GET PROPERTY DETAILS
    public PropertyDetails getPropertyDetails(int propertyId) {

        String sql = """
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
                    WHERE p.property_id = ?
                """;

        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            PropertyDetails p = new PropertyDetails();

            p.setPropertyId(rs.getInt("property_id"));
            p.setStreetAddress(rs.getString("street_address"));
            p.setPropertyType(rs.getString("property_type"));
            p.setBedrooms(rs.getInt("bedrooms"));
            p.setBathrooms(rs.getDouble("bathrooms"));
            p.setSquareFeet(rs.getInt("square_feet"));
            p.setYearBuilt(rs.getInt("year_built"));

            p.setCity(rs.getString("city"));
            p.setState(rs.getString("state"));
            p.setZipCode(rs.getString("zip_code"));

            p.setListPrice(rs.getDouble("list_price"));
            p.setListingStatus(rs.getString("listing_status"));
            p.setDaysOnMarket(rs.getInt("days_on_market"));

            return p;

        }, propertyId);
    }

    // GET PRICE HISTORY
    public List<PriceHistory> getPriceHistory(int propertyId) {

        String sql = """
                    SELECT recorded_date, price
                    FROM price_history
                    WHERE property_id = ?
                    ORDER BY recorded_date DESC
                """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            PriceHistory ph = new PriceHistory();
            ph.setRecordedDate(rs.getDate("recorded_date").toLocalDate());
            ph.setPrice(rs.getDouble("price"));
            return ph;
        }, propertyId);
    }
}
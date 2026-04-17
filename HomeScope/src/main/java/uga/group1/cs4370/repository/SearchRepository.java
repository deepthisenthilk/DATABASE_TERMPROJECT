package uga.group1.cs4370.repository;

import uga.group1.cs4370.model.SearchResult;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SearchRepository {

    private final JdbcTemplate jdbcTemplate;

    public SearchRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<SearchResult> searchProperties(
            String city,
            double minPrice,
            double maxPrice,
            int bedrooms) {

        String sql = """
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
                    ORDER BY l.list_price ASC
                """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            SearchResult r = new SearchResult();

            r.setListingId(rs.getInt("listing_id"));
            r.setPropertyId(rs.getInt("property_id"));
            r.setStreetAddress(rs.getString("street_address"));
            r.setCity(rs.getString("city"));
            r.setState(rs.getString("state"));
            r.setZipCode(rs.getString("zip_code"));
            r.setBedrooms(rs.getInt("bedrooms"));
            r.setBathrooms(rs.getDouble("bathrooms"));
            r.setSquareFeet(rs.getInt("square_feet"));
            r.setListPrice(rs.getDouble("list_price"));
            r.setListingStatus(rs.getString("listing_status"));

            return r;
        }, city, minPrice, maxPrice, bedrooms);
    }
}
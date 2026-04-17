package uga.group1.cs4370.repository;

import uga.group1.cs4370.model.MarketStats;
import uga.group1.cs4370.model.PricePerSqftStats;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DashboardRepository {

    private final JdbcTemplate jdbcTemplate;

    public DashboardRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // QUERY 1: average price by city
    public List<MarketStats> getMarketStats() {

        String sql = """
                    SELECT
                        loc.city,
                        COUNT(*) AS total_listings,
                        AVG(l.list_price) AS avg_price
                    FROM listings l
                    JOIN properties p ON l.property_id = p.property_id
                    JOIN locations loc ON p.location_id = loc.location_id
                    WHERE l.listing_status = 'Active'
                    GROUP BY loc.city
                    ORDER BY avg_price DESC
                """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            MarketStats m = new MarketStats();
            m.setCity(rs.getString("city"));
            m.setTotalListings(rs.getInt("total_listings"));
            m.setAvgPrice(rs.getDouble("avg_price"));
            return m;
        });
    }

    // QUERY 2: price per sqft
    public List<PricePerSqftStats> getPricePerSqft() {

        String sql = """
                    SELECT
                        loc.city,
                        AVG(l.list_price / p.square_feet) AS avg_price_per_sqft
                    FROM listings l
                    JOIN properties p ON l.property_id = p.property_id
                    JOIN locations loc ON p.location_id = loc.location_id
                    WHERE p.square_feet > 0
                    GROUP BY loc.city
                """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            PricePerSqftStats d = new PricePerSqftStats();
            d.setCity(rs.getString("city"));
            d.setAvgPricePerSqft(rs.getDouble("avg_price_per_sqft"));
            return d;
        });
    }
}
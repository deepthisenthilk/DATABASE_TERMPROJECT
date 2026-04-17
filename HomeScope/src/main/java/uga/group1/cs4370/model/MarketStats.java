package uga.group1.cs4370.model;

public class MarketStats {

    private String city;
    private int totalListings;
    private double avgPrice;

    public MarketStats() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getTotalListings() {
        return totalListings;
    }

    public void setTotalListings(int totalListings) {
        this.totalListings = totalListings;
    }

    public double getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(double avgPrice) {
        this.avgPrice = avgPrice;
    }
}
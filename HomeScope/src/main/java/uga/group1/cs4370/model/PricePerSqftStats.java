package uga.group1.cs4370.model;

public class PricePerSqftStats {

    private String city;
    private double avgPricePerSqft;

    public PricePerSqftStats() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getAvgPricePerSqft() {
        return avgPricePerSqft;
    }

    public void setAvgPricePerSqft(double avgPricePerSqft) {
        this.avgPricePerSqft = avgPricePerSqft;
    }
}
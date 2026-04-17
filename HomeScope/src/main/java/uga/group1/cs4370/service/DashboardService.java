package uga.group1.cs4370.service;

import uga.group1.cs4370.model.MarketStats;
import uga.group1.cs4370.model.PricePerSqftStats;
import uga.group1.cs4370.repository.DashboardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {

    private final DashboardRepository dashboardRepository;

    public DashboardService(DashboardRepository dashboardRepository) {
        this.dashboardRepository = dashboardRepository;
    }

    public List<MarketStats> getMarketStats() {
        return dashboardRepository.getMarketStats();
    }

    public List<PricePerSqftStats> getPricePerSqft() {
        return dashboardRepository.getPricePerSqft();
    }
}
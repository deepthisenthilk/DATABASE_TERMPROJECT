package uga.group1.cs4370.service;

import uga.group1.cs4370.model.PropertyDetails;
import uga.group1.cs4370.repository.PropertyRepository;
import org.springframework.stereotype.Service;

@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;

    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    public PropertyDetails getProperty(int propertyId) {

        PropertyDetails details = propertyRepository.getPropertyDetails(propertyId);

        details.setPriceHistory(
                propertyRepository.getPriceHistory(propertyId));

        return details;
    }
}
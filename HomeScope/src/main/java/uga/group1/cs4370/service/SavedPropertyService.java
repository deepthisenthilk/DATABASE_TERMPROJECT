package uga.group1.cs4370.service;

import uga.group1.cs4370.model.SavedPropertyView;
import uga.group1.cs4370.repository.SavedPropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SavedPropertyService {

    private final SavedPropertyRepository savedPropertyRepository;

    public SavedPropertyService(SavedPropertyRepository savedPropertyRepository) {
        this.savedPropertyRepository = savedPropertyRepository;
    }

    public void save(int userId, int propertyId) {
        savedPropertyRepository.saveProperty(userId, propertyId);
    }

    public void remove(int userId, int propertyId) {
        savedPropertyRepository.removeProperty(userId, propertyId);
    }

    public List<SavedPropertyView> getSaved(int userId) {
        return savedPropertyRepository.getSavedProperties(userId);
    }
}
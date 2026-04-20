package uga.group1.cs4370.controller;

import uga.group1.cs4370.model.PropertyDetails;
import uga.group1.cs4370.service.PropertyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PropertyController {

    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @GetMapping("/property/{propertyId}")
    public String getPropertyDetails(@PathVariable int propertyId, Model model) {

        PropertyDetails property = propertyService.getProperty(propertyId);

        if (property == null) {
            model.addAttribute("error", "Property not found.");
            return "search";
        }

        model.addAttribute("propertyId", property.getPropertyId());
        model.addAttribute("streetAddress", property.getStreetAddress());
        model.addAttribute("city", property.getCity());
        model.addAttribute("state", property.getState());
        model.addAttribute("zipCode", property.getZipCode());
        model.addAttribute("propertyType", property.getPropertyType());
        model.addAttribute("bedrooms", property.getBedrooms());
        model.addAttribute("bathrooms", property.getBathrooms());
        model.addAttribute("squareFeet", property.getSquareFeet());
        model.addAttribute("yearBuilt", property.getYearBuilt());
        model.addAttribute("listPrice", property.getListPrice());
        model.addAttribute("listingStatus", property.getListingStatus());
        model.addAttribute("daysOnMarket", property.getDaysOnMarket());
        model.addAttribute("priceHistory", property.getPriceHistory());

        return "property-details";
    }
}

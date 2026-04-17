package uga.group1.cs4370.controller;

import uga.group1.cs4370.service.SearchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/search")
    public String search(
            @RequestParam(required = false, defaultValue = "") String city,
            @RequestParam(required = false, defaultValue = "0") double minPrice,
            @RequestParam(required = false, defaultValue = "100000000") double maxPrice,
            @RequestParam(required = false, defaultValue = "0") int bedrooms,
            Model model) {

        if (!city.isEmpty()) {
            model.addAttribute("properties",
                    searchService.search(city, minPrice, maxPrice, bedrooms));
        }

        return "search";
    }
}
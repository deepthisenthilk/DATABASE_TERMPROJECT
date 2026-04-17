package uga.group1.cs4370.service;

import uga.group1.cs4370.model.SearchResult;
import uga.group1.cs4370.repository.SearchRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    private final SearchRepository searchRepository;

    public SearchService(SearchRepository searchRepository) {
        this.searchRepository = searchRepository;
    }

    public List<SearchResult> search(String city, double minPrice, double maxPrice, int bedrooms) {
        return searchRepository.searchProperties(city, minPrice, maxPrice, bedrooms);
    }
}
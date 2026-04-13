package uga.group1.cs4370.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    // Home page
    @GetMapping("/")
    public String home() {
        return "login";
    }

    // Login page
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // Signup page
    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    // Search page
    @GetMapping("/search")
    public String search(Model model) {
        return "search";
    }

    // Property details page
    @GetMapping("/property")
    public String propertyDetails() {
        return "property-details";
    }

    // Saved properties page
    @GetMapping("/saved-properties")
    public String savedProperties() {
        return "saved-properties";
    }

    // Dashboard page
    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }
}
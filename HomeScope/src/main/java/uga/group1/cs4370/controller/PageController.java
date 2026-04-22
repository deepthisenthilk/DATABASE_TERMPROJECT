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

    
    // Property details page
    @GetMapping("/property")
    public String propertyDetails() {
        return "property-details";
    }

}

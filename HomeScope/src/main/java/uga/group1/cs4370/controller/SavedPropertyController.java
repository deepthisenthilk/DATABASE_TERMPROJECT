package uga.group1.cs4370.controller;

import uga.group1.cs4370.model.User;
import uga.group1.cs4370.service.SavedPropertyService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SavedPropertyController {
    
    private final SavedPropertyService savedPropertyService;

    public SavedPropertyController(SavedPropertyService savedPropertyService) {
        this.savedPropertyService = savedPropertyService;
    }

    @GetMapping("/saved-properties")
    public String showSavedProperties(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        model.addAttribute("savedProperties", savedPropertyService.getSaved(user.getUserId()));
        return "saved-properties";
    }


    @PostMapping("/saved-properties/add")
    public String saveProperty(@RequestParam int propertyId,
                               HttpSession session) {

        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }

        User user = (User) session.getAttribute("user");

        savedPropertyService.save(user.getUserId(), propertyId);

        return "redirect:/saved-properties";
    }


    @PostMapping("/saved-properties/remove")
    public String removeProperty(HttpSession session, @RequestParam int propertyId) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }

        User user = (User) session.getAttribute("user");

       savedPropertyService.remove(user.getUserId(), propertyId);
        return "redirect:/saved-properties";
    }
}

package uga.group1.cs4370.controller;

import uga.group1.cs4370.model.User;
import uga.group1.cs4370.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // Logging in
    @PostMapping("/login")
    public String login(@RequestParam String email,
            @RequestParam String password,
            HttpSession session,
            Model model) {

        User user = userService.login(email, password);

        if (user != null) {
            session.setAttribute("user", user);
            return "redirect:/dashboard";
        }

        model.addAttribute("error", "Invalid email or password");
        return "login";
    }

    // Signing up
    @PostMapping("/signup")
    public String signup(@RequestParam String fullName,
            @RequestParam String email,
            @RequestParam String password,
            Model model) {

        boolean success = userService.registerUser(fullName, email, password);

        if (success) {
            model.addAttribute("message", "Account created! Please login.");
            return "login";
        }

        model.addAttribute("error", "Email already exists");
        return "signup";
    }

    // Logging out
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
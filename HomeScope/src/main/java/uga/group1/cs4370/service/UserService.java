package uga.group1.cs4370.service;

import uga.group1.cs4370.model.User;
import uga.group1.cs4370.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Signing up
    public boolean registerUser(String fullName, String email, String password) {

        if (userRepository.emailExists(email)) {
            return false;
        }

        userRepository.createUser(fullName, email, password);

        return true;
    }

    // Logging in
    public User login(String email, String password) {

        User user = userRepository.findByEmail(email);

        if (user == null)
            return null;

        if (user.getPasswordHash().equals(password)) {
            return user;
        }

        return null;
    }
}
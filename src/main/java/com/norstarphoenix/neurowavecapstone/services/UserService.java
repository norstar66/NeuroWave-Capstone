package com.norstarphoenix.neurowavecapstone.services;

import com.norstarphoenix.neurowavecapstone.models.User;
import com.norstarphoenix.neurowavecapstone.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean authenticate(String username, String password) {
        // Retrieve the user by username
        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            // Compare the provided password with the stored password hash
            return passwordEncoder.matches(password, user.getPasswordHash());
        }
        return false; // Authentication failed
    }

    public void registerUser(User user) {
        // Encrypt the user's password before saving
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        userRepository.save(user);
    }
}
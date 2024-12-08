package com.norstarphoenix.neurowavecapstone.services;

package com.norstarphoenix.neurowavecapstone.services;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    public boolean authenticate(String username, String password) {
        // Add logic to validate user credentials (e.g., check the database)
        return "admin".equals(username) && "password".equals(password); // Example logic
    }
}
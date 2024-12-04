package com.norstarphoenix.neurowavecapstone.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Setter
@Getter
@Entity
public class User {
    // Getters and Setters
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private String username;
    @Setter
    private String token;
    @jakarta.persistence.Id
    private Long user_id;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public void setUser_id(Long userId) {
        this.user_id = userId;
    }


}

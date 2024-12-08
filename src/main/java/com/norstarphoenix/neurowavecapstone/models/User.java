package com.norstarphoenix.neurowavecapstone.models;

import jakarta.persistence.Transient;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="users")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary key for the User entity

    @NotNull
    @Size(min = 3, max = 50)
    private String username; // User's name

    @Transient
    private String token; // Authentication or session token

    @Column(nullable = false)
    private String passwordHash; // Encrypted password stored

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;

    }
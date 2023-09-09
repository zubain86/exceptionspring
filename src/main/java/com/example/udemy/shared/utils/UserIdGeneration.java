package com.example.udemy.shared.utils;

import org.springframework.stereotype.Component;

import java.util.UUID;
@Component
public class UserIdGeneration {
    public String createUserId() {
        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString();
        return uuidAsString;
    }
}
package io.github.clientgrant.bean;

import lombok.Data;

@Data
public class UserProfile {
    private String name;
    private String email;

    public UserProfile(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
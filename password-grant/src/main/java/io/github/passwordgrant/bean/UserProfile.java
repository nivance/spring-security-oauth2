package io.github.passwordgrant.bean;

import lombok.Data;

@Data
public class UserProfile {
    private String username;
    private String email;

    public UserProfile(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
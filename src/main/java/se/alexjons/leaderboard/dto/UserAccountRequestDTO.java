package se.alexjons.leaderboard.dto;

import jakarta.validation.constraints.Size;

public class UserAccountRequestDTO {

    @Size(min = 2, message = "username cannot be less than two characters")
    private String username;

    @Size(min = 5, message = "password has to be more than four characters")
    private String password;

    public UserAccountRequestDTO() {
    }

    public UserAccountRequestDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

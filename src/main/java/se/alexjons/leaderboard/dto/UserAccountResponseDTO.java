package se.alexjons.leaderboard.dto;

import jakarta.validation.constraints.Size;

public class UserAccountResponseDTO {

    private String username;
    private Long score;

    public UserAccountResponseDTO() {
    }

    public UserAccountResponseDTO(String username, Long score) {
        this.username = username;
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public Long getScore() {
        return score;
    }
}

package se.alexjons.leaderboard.dto;

import se.alexjons.leaderboard.entity.UserRole;

import java.util.List;

public class UserAccountResponseDTO {

    private String username;
    private Long score;
    private List<String> roles;

    public UserAccountResponseDTO() {
    }

    public UserAccountResponseDTO(String username, Long score, List<String> roles) {
        this.username = username;
        this.score = score;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public Long getScore() {
        return score;
    }

    public List<String> getRoles() {
        return roles;
    }
}

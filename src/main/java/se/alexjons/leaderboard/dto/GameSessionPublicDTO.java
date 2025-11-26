package se.alexjons.leaderboard.dto;

import se.alexjons.leaderboard.entity.UserAccount;

public class GameSessionPublicDTO {

    private Long id;
    private String username;
    private Long score;
    private Long duration;

    public GameSessionPublicDTO() {
    }

    public GameSessionPublicDTO(Long id, String username, Long score, Long duration) {
        this.id = id;
        this.username = username;
        this.score = score;
        this.duration = duration;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Long getScore() {
        return score;
    }

    public Long getDuration() {
        return duration;
    }
}

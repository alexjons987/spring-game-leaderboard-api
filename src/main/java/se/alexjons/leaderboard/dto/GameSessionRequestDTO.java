package se.alexjons.leaderboard.dto;

import jakarta.validation.constraints.Positive;

public class GameSessionRequestDTO {

    @Positive(message = "score cannot be less than 0")
    private Long score;

    @Positive(message = "duration cannot be less than 0")
    private Long duration;

    public GameSessionRequestDTO() {
    }

    public GameSessionRequestDTO(Long score, Long duration) {
        this.score = score;
        this.duration = duration;
    }

    public Long getScore() {
        return score;
    }

    public Long getDuration() {
        return duration;
    }
}

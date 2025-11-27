package se.alexjons.leaderboard.dto;

public class GameSessionResponseDTO {

    private Long id;
    private Long score;
    private Long duration;

    public GameSessionResponseDTO() {
    }

    public GameSessionResponseDTO(Long id, Long score, Long duration) {
        this.id = id;
        this.score = score;
        this.duration = duration;
    }

    public Long getId() {
        return id;
    }

    public Long getScore() {
        return score;
    }

    public Long getDuration() {
        return duration;
    }
}

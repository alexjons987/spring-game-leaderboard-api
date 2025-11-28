package se.alexjons.leaderboard.exception;

public class AchievementNotFoundException extends RuntimeException {
    public AchievementNotFoundException(String message) {
        super(message);
    }
}

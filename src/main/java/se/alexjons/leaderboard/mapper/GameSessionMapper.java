package se.alexjons.leaderboard.mapper;

import org.springframework.stereotype.Component;
import se.alexjons.leaderboard.dto.GameSessionDTO;
import se.alexjons.leaderboard.dto.GameSessionPublicDTO;
import se.alexjons.leaderboard.entity.GameSession;

@Component
public class GameSessionMapper {

    public GameSessionDTO toDTO(GameSession gameSession) {
        if (gameSession == null) return null;

        return new GameSessionDTO(
                gameSession.getId(),
                gameSession.getScore(),
                gameSession.getDuration()
        );
    }

    public GameSessionPublicDTO toPublicDTO(GameSession gameSession) {
        if (gameSession == null) return null;

        return new GameSessionPublicDTO(
                gameSession.getId(),
                gameSession.getUserAccount().getUsername(),
                gameSession.getScore(),
                gameSession.getDuration()
        );
    }
}

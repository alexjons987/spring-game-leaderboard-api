package se.alexjons.leaderboard.mapper;

import org.springframework.stereotype.Component;
import se.alexjons.leaderboard.dto.GameSessionResponseDTO;
import se.alexjons.leaderboard.dto.GameSessionPublicDTO;
import se.alexjons.leaderboard.entity.GameSession;

@Component
public class GameSessionMapper {

    public GameSessionResponseDTO toDTO(GameSession gameSession) {
        if (gameSession == null) return null;

        return new GameSessionResponseDTO(
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

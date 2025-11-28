package se.alexjons.leaderboard.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import se.alexjons.leaderboard.dto.GameSessionRequestDTO;
import se.alexjons.leaderboard.dto.GameSessionResponseDTO;
import se.alexjons.leaderboard.dto.GameSessionPublicDTO;
import se.alexjons.leaderboard.entity.GameSession;
import se.alexjons.leaderboard.entity.UserAccount;
import se.alexjons.leaderboard.entity.UserAchievement;
import se.alexjons.leaderboard.exception.AchievementNotFoundException;
import se.alexjons.leaderboard.mapper.GameSessionMapper;
import se.alexjons.leaderboard.repository.AchievementRepository;
import se.alexjons.leaderboard.repository.GameSessionRepository;
import se.alexjons.leaderboard.repository.UserRepository;

import java.util.List;
import java.util.Set;

@Service
public class GameSessionService {

    @Autowired
    GameSessionRepository gameSessionRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AchievementRepository achievementRepository;

    @Autowired
    GameSessionMapper gameSessionMapper;

    public List<GameSessionPublicDTO> getAllGameSessions() {
        return gameSessionRepository.findAll().stream()
                .map(gameSessionMapper::toPublicDTO)
                .toList();
    }

    public List<GameSessionResponseDTO> getGameSessionsForCurrentUser(Authentication authentication) {

        UserAccount user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));


        return gameSessionRepository.findByUserAccount_Id(user.getId()).stream()
                .map(gameSessionMapper::toDTO)
                .toList();
    }

    public List<GameSessionPublicDTO> getTopGameSessions() {
        return gameSessionRepository.findTop10ByOrderByScoreDesc().stream()
                .map(gameSessionMapper::toPublicDTO)
                .toList();
    }

    @Transactional
    public GameSessionResponseDTO addNewGameSession(GameSessionRequestDTO dto, Authentication authentication) {

        UserAccount user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        GameSession gameSession = new GameSession();

        gameSession.setUserAccount(user);
        gameSession.setScore(dto.getScore());
        gameSession.setDuration(dto.getDuration());

        GameSession savedGameSession = gameSessionRepository.save(gameSession); // Save new game session

        Long newScore = user.getScore() + dto.getScore();
        user.setScore(newScore); // Update total score in user

        updateUserAchievements(user, gameSession); // Update user achievements

        userRepository.save(user);

        return gameSessionMapper.toDTO(savedGameSession);
    }

    private void updateUserAchievements(UserAccount userAccount, GameSession gameSession) {
        Set<UserAchievement> userAchievements = userAccount.getAchievements();

        addAchievementIfMissing(userAccount, "First Blood");

        if (userAccount.getScore() >= 1000) {
            addAchievementIfMissing(userAccount, "Damage Dealer");
        }

        if (gameSession.getScore() >= 1000) {
            addAchievementIfMissing(userAccount, "NET Master");
        }
    }

    private void addAchievementIfMissing(UserAccount user, String name) {
        UserAchievement achievement = achievementRepository.findByName(name)
                .orElseThrow(() -> new AchievementNotFoundException("Achievement not found: " + name));

        if (!user.getAchievements().contains(achievement)) {
            user.getAchievements().add(achievement);
        }
    }
}

package se.alexjons.leaderboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import se.alexjons.leaderboard.dto.GameSessionDTO;
import se.alexjons.leaderboard.dto.GameSessionPublicDTO;
import se.alexjons.leaderboard.entity.UserAccount;
import se.alexjons.leaderboard.mapper.GameSessionMapper;
import se.alexjons.leaderboard.repository.GameSessionRepository;
import se.alexjons.leaderboard.repository.UserRepository;

import java.util.List;

@Service
public class GameSessionService {

    @Autowired
    GameSessionRepository gameSessionRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    GameSessionMapper gameSessionMapper;

    public List<GameSessionPublicDTO> getAllGameSessions() {
        return gameSessionRepository.findAll().stream()
                .map(gameSessionMapper::toPublicDTO)
                .toList();
    }

    public List<GameSessionDTO> getGameSessionsForCurrentUser(Authentication authentication) {

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
}

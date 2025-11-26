package se.alexjons.leaderboard.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import se.alexjons.leaderboard.dto.GameSessionDTO;
import se.alexjons.leaderboard.dto.GameSessionPublicDTO;
import se.alexjons.leaderboard.service.GameSessionService;

import java.util.List;

@RestController
@RequestMapping("/sessions")
public class GameSessionController {

    @Autowired
    GameSessionService gameSessionService;

    // Admin
    // Get ALL sessions of user
    @GetMapping("/all")
    public ResponseEntity<List<GameSessionPublicDTO>> getAllSessions() {
        return ResponseEntity.ok(gameSessionService.getAllGameSessions());
    }

    // Delete session
    @DeleteMapping("/delete")
    public ResponseEntity<List<GameSessionPublicDTO>> deleteSession(@RequestParam Long sessionId) {
        return ResponseEntity.status(501).build(); // TODO: Implement
    }

    // User
    // Add session
    @PostMapping
    public ResponseEntity<GameSessionDTO> addNewSession(@Valid @RequestBody GameSessionDTO gameSessionDTO) {
        return ResponseEntity.status(501).build(); // TODO: Implement
    }

    @GetMapping
    public ResponseEntity<List<GameSessionDTO>> getUserSessions(Authentication authentication) {
        return ResponseEntity.ok(gameSessionService.getGameSessionsForCurrentUser(authentication));
    }

    @GetMapping("/top")
    public ResponseEntity<List<GameSessionPublicDTO>> getGlobalTopSessions() {
        return ResponseEntity.ok(gameSessionService.getTopGameSessions());
    }
}

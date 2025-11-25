package se.alexjons.leaderboard.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import se.alexjons.leaderboard.dto.UserAccountRequestDTO;
import se.alexjons.leaderboard.dto.UserAccountResponseDTO;
import se.alexjons.leaderboard.service.AuthService;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserAccountResponseDTO> register(
            @Valid @RequestBody UserAccountRequestDTO userAccountRequestDTO) {
        return ResponseEntity.ok(authService.addNewUser(userAccountRequestDTO));
    }
}

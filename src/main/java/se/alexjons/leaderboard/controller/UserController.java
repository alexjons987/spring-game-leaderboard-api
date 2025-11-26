package se.alexjons.leaderboard.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import se.alexjons.leaderboard.dto.UserAccountRequestDTO;
import se.alexjons.leaderboard.dto.UserAccountResponseDTO;
import se.alexjons.leaderboard.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<UserAccountResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}

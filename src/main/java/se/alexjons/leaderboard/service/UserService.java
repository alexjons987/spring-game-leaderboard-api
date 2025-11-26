package se.alexjons.leaderboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.alexjons.leaderboard.dto.UserAccountRequestDTO;
import se.alexjons.leaderboard.dto.UserAccountResponseDTO;
import se.alexjons.leaderboard.entity.UserAccount;
import se.alexjons.leaderboard.entity.UserRole;
import se.alexjons.leaderboard.exception.UsernameAlreadyTakenException;
import se.alexjons.leaderboard.mapper.UserMapper;
import se.alexjons.leaderboard.repository.RoleRepository;
import se.alexjons.leaderboard.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserMapper userMapper;

    public List<UserAccountResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toResponseDTO)
                .toList();
    }
}

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

import java.util.Optional;
import java.util.Set;

@Service
public class AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserMapper userMapper;

    public UserAccountResponseDTO addNewUser(UserAccountRequestDTO userAccountRequestDTO) {
        Optional<UserAccount> existingUser = userRepository.findByUsername(userAccountRequestDTO.getUsername());

        if (existingUser.isPresent()) {
            throw new UsernameAlreadyTakenException("Username already taken!");
        }

        UserAccount userAccount = new UserAccount();
        userAccount.setUsername(userAccountRequestDTO.getUsername());
        userAccount.setPassword(passwordEncoder.encode(userAccountRequestDTO.getPassword()));
        userAccount.setScore(0L);

        UserRole userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new IllegalStateException("Role ROLE_USER not found"));

        userAccount.setRoles(Set.of(userRole));

        return userMapper.toResponseDTO(userRepository.save(userAccount));
    }
}

package se.alexjons.leaderboard.mapper;

import org.springframework.stereotype.Component;
import se.alexjons.leaderboard.dto.UserAccountResponseDTO;
import se.alexjons.leaderboard.entity.UserAccount;
import se.alexjons.leaderboard.entity.UserRole;

@Component
public class UserMapper {

    public UserAccountResponseDTO toResponseDTO(UserAccount userAccount) {
        if (userAccount == null) return null;

        return new UserAccountResponseDTO(
                userAccount.getUsername(),
                userAccount.getScore(),
                userAccount.getRoles().stream()
                        .map(UserRole::getName)
                        .toList()
        );
    }
}

package se.alexjons.leaderboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.alexjons.leaderboard.entity.UserAchievement;

import java.util.Optional;

@Repository
public interface AchievementRepository extends JpaRepository<UserAchievement, Long> {
    Optional<UserAchievement> findByName(String name);
}

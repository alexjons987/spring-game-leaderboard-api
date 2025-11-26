package se.alexjons.leaderboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.alexjons.leaderboard.entity.GameSession;

import java.util.List;

@Repository
public interface GameSessionRepository extends JpaRepository<GameSession, Long> {

    List<GameSession> findTop10ByOrderByScoreDesc();

    List<GameSession> findByUserAccount_Id(Long userId);

}

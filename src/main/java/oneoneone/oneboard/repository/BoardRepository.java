package oneoneone.oneboard.repository;

import oneoneone.oneboard.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<BoardEntity , Long> {
}

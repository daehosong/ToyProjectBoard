package oneoneone.oneboard.repository;

import oneoneone.oneboard.dto.CommentDTO;
import oneoneone.oneboard.entity.BoardEntity;
import oneoneone.oneboard.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity,Long> {
    List<CommentEntity> findAllByBoardEntityOrderByIdDesc(BoardEntity boardEntity);
}

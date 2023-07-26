package oneoneone.oneboard.repository;

import oneoneone.oneboard.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<BoardEntity , Long> {

    //  @Query(value = "update BoardEntity b set b.boardHits=b.boardHits+1 where b.id=id" , nativeQuery=true)
    //  nativeQuery를 사용하게 되면 실제 DB에서 쓰는 쿼리 사용 가능
    //  update board_table set board_hits=board_hits+1 where id=?
    //  @Modifying : update나 delete 쿼리를 진행 해야 할 경우 Modifying 작성
    @Modifying
    @Query(value = "update BoardEntity b set b.boardHits=b.boardHits+1 where b.id=id")
    void updateHits(@Param("id")Long boardId);
}

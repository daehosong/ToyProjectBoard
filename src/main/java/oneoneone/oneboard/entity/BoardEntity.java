package oneoneone.oneboard.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import oneoneone.oneboard.dto.BoardDTO;

import javax.persistence.*;
import java.time.LocalDateTime;

//  spring data jpa 테이블
@Entity
@Getter @Setter @Table(name = "board_table")
public class BoardEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private Long boardId;

    @Column
    private String boardWriter;
    //  회원과 게시판 1:N 관계 설정

    @Column
    private String postHeader;
    @Column
    private String postContent;
    @Column
    private LocalDateTime createdTime;
    @Column
    private LocalDateTime updatedTime;
    @Column
    private int boardHits;

    public static BoardEntity toSaveEntity(BoardDTO boardDTO){
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setPostHeader(boardDTO.getPostHeader());
        boardEntity.setPostContent(boardDTO.getPostContent());
        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setBoardHits(0);
        return boardEntity;
    }
    
}

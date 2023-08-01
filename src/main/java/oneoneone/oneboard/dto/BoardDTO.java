package oneoneone.oneboard.dto;

import lombok.*;
import oneoneone.oneboard.entity.BoardEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor // 기본생성자
@AllArgsConstructor // 모든 필드를 매개변수로 하는 생성자
public class BoardDTO {
    private Long id;
    private String boardWriter;
    private String boardPass;
    private String boardTitle;
    private String boardContents;
    private int boardHits;
    private LocalDateTime boardCreatedTime;
    private LocalDateTime boardUpdatedTime;

    public BoardDTO(Long id, String boardWriter, String boardTitle, int boardHits, LocalDateTime boardCreatedTime) {
        this.id = id;
        this.boardWriter = boardWriter;
        this.boardTitle = boardTitle;
        this.boardHits = boardHits;
        this.boardCreatedTime = boardCreatedTime;
    }

    public static BoardDTO toBoardDTO(BoardEntity boardEntity) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(boardEntity.getId());
        boardDTO.setBoardWriter(boardEntity.getBoardWriter());
        boardDTO.setBoardPass(boardEntity.getBoardPass());
        boardDTO.setBoardTitle(boardEntity.getBoardTitle());
        boardDTO.setBoardContents(boardEntity.getBoardContents());
        boardDTO.setBoardHits(boardEntity.getBoardHits());
        boardDTO.setBoardCreatedTime(boardEntity.getCreatedTime());
        boardDTO.setBoardUpdatedTime(boardEntity.getUpdatedTime());
        return boardDTO;
    }
}


//  Board Data Transfer Object ,VO , BEAN

/*
@Getter @Setter
@ToString @NoArgsConstructor @AllArgsConstructor
public class BoardDTO {
    private Long boardId;
    private String postHeader;
    private String postContent;
    private String boardWriter;
    private String boardPass;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    //  회원과 게시판 관계설정
    //  private String memberEmail;
    private int boardHits;

    public static BoardDTO toBoardDTO(BoardEntity boardEntity){
    BoardDTO boardDTO = new BoardDTO();
    boardDTO.setBoardId(boardEntity.getBoardId());
    boardDTO.setBoardWriter(boardEntity.getBoardWriter());
    boardDTO.setBoardPass(boardEntity.getBoardPass());
    boardDTO.setPostHeader(boardEntity.getPostHeader());
    boardDTO.setPostContent(boardEntity.getPostContent());
    boardDTO.setBoardHits(boardEntity.getBoardHits());
    boardDTO.setCreatedTime(boardEntity.getCreatedTime());
    boardDTO.setUpdatedTime(boardEntity.getUpdatedTime());
    return boardDTO;
    }

}
*/

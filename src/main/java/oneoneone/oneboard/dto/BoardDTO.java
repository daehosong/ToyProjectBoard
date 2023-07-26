package oneoneone.oneboard.dto;

import lombok.*;
import oneoneone.oneboard.entity.BoardEntity;

import java.time.LocalDateTime;

//  Board Data Transfer Object ,VO , BEAN

@Getter @Setter
@ToString @NoArgsConstructor @AllArgsConstructor
public class BoardDTO {
    private Long boardId;
    private String postHeader;
    private String postContent;
    private String boardWriter;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    //  회원과 게시판 관계설정
    //  private String memberEmail;
    private int boardHits;

    public static BoardDTO toBoardDTO(BoardEntity boardEntity){
    BoardDTO boardDTO = new BoardDTO();
    boardDTO.setBoardId(boardEntity.getBoardId());
    boardDTO.setBoardWriter(boardEntity.getBoardWriter());
    boardDTO.setPostHeader(boardEntity.getPostHeader());
    boardDTO.setPostContent(boardEntity.getPostContent());
    boardDTO.setBoardHits(boardEntity.getBoardHits());
    boardDTO.setCreatedTime(boardEntity.getCreatedTime());
    boardDTO.setUpdatedTime(boardEntity.getUpdatedTime());
    return boardDTO;
    }

}

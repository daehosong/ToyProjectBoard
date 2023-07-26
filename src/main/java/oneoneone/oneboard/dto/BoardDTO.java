package oneoneone.oneboard.dto;

import lombok.*;

import java.time.LocalDateTime;

//  Board Data Transfer Object ,VO , BEAN

@Getter @Setter
@ToString @NoArgsConstructor @AllArgsConstructor
public class BoardDTO {
    private Long boardId;
    private String postHeader;
    private String postContent;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private int boardHits;
    //  회원과 게시판 관계설정
//    private String memberEmail;
    private String boardWriter;

}

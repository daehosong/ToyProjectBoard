package oneoneone.oneboard.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter @Table(name = "board_table")
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    @Column
    private String postHeader;
    @Column
    private String postContent;
    @Column
    private LocalDateTime createdTime;

    //  회원과 게시판 1:N 관계 설정

}

package site.brucewu.aha.dao.mb.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDateTime;

@Entity(name = "aha_message_board_comment_main")
@Data
@DynamicInsert
public class AhaMessageBoardCommentMain {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @Column(name = "ambm_id")
    private Long ambmId;

    @Column(name = "name")
    private String name;

    @Column(name = "ip")
    private String ip;

    @Column(name = "like_num")
    private Long likeNum;

    @Column(name = "content_text")
    private String contentText;
}

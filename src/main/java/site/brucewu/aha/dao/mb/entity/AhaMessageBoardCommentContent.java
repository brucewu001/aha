package site.brucewu.aha.dao.mb.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDateTime;

@Entity(name = "aha_message_board_comment_content")
@Data
@DynamicInsert
public class AhaMessageBoardCommentContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @Column(name = "ambcm_id")
    private Long ambcmId;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "content")
    private String content;
}

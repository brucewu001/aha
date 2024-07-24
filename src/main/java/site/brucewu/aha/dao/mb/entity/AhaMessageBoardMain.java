package site.brucewu.aha.dao.mb.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDateTime;

@Entity(name = "aha_message_board_main")
@Data
@DynamicInsert
public class AhaMessageBoardMain{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @Column(name = "name")
    private String name;

    @Column(name = "ip")
    private String ip;

    @Column(name = "comment_num")
    private Integer commentNum;

    @Column(name = "like_num")
    private Integer likeNum;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "session_key")
    private String sessionKey;

    @Column(name = "anonymous_enable")
    private Boolean anonymousEnable;

    @Column(name = "content_text")
    private String contentText;
}

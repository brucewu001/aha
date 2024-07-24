package site.brucewu.aha.dao.mb.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDateTime;

@Entity(name = "aha_message_board_content")
@Data
@DynamicInsert
public class AhaMessageBoardContent {

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

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "content")
    private String content;
}

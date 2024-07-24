package site.brucewu.aha.service.mb.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class MessageBoardDto {

    private Long id;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Long userId;

    private Boolean anonymousEnable;

    private String sessionKey;

    private String name;

    private Integer commentNum;

    private Integer likeNum;

    private String ip;

    private String contentText;

    private List<MessageBoardContentDto> mbcList;
}

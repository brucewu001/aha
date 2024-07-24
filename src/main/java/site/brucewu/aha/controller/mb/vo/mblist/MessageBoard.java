package site.brucewu.aha.controller.mb.vo.mblist;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;


import java.time.LocalDateTime;
import java.util.List;


@Data
public class MessageBoard {
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    private Long userId;

    private Boolean anonymousEnable;

    private String sessionKey;

    private String name;

    private Integer commentNum;

    private Integer likeNum;

    private String ip;

    private String contentText;

    private List<MessageBoardContent> mbcList;

}

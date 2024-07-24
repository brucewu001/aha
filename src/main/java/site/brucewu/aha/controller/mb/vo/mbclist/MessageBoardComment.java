package site.brucewu.aha.controller.mb.vo.mbclist;

import com.fasterxml.jackson.annotation.JsonFormat;


import java.time.LocalDateTime;
import java.util.List;

public class MessageBoardComment {

    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    private Long amb_id;

    private String name;

    private Integer likenum;
    private String ip;

    private List<MessageBoardCommentContent> mbcList;

}

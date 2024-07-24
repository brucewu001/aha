package site.brucewu.aha.service.mb.dto;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageBoardContentDto {
    
    private Long id;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String contentType;

    private String content;
}

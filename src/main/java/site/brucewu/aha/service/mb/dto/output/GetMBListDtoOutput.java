package site.brucewu.aha.service.mb.dto.output;

import lombok.Data;
import site.brucewu.aha.service.mb.dto.MessageBoardDto;

import java.util.List;

@Data
public class GetMBListDtoOutput {

    private List<MessageBoardDto> mbList;

    private Integer currentPage;

    private Integer pageSize;

    private Integer currentPageSize;

    private Integer totalPage;
}

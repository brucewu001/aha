package site.brucewu.aha.controller.mb.vo.mblist.output;

import lombok.Data;
import site.brucewu.aha.controller.mb.vo.mblist.MessageBoard;

import java.io.Serializable;
import java.util.List;

@Data
public class GetMBListOutput implements Serializable {

    private List<MessageBoard> mbList;

    private Integer currentPage;

    private Integer currentPageSize;

    private Integer PageSize;

    private Integer totalPage;
}

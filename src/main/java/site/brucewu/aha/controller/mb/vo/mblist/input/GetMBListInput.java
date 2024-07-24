package site.brucewu.aha.controller.mb.vo.mblist.input;

import lombok.Data;

@Data
public class GetMBListInput {

    private String keyword;

    private String sortType;

    private String sessionKey;

    private String sortRule;

    private Integer currentPage;

    private Integer pageSize;

}

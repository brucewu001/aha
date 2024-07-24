package site.brucewu.aha.service.mb.dto.input;

import lombok.Data;

@Data
public class GetMBListDtoInput {

    private String keyword;

    private String sortType;

    private String sessionKey;

    private String sortRule;

    private Integer currentPage;

    private Integer pageSize;

}

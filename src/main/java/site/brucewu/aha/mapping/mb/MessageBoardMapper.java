package site.brucewu.aha.mapping.mb;

import org.mapstruct.Mapper;
import site.brucewu.aha.controller.mb.vo.mblist.input.GetMBListInput;
import site.brucewu.aha.controller.mb.vo.mblist.input.PostMBListInput;
import site.brucewu.aha.service.mb.dto.input.GetMBListDtoInput;
import site.brucewu.aha.service.mb.dto.input.SaveMessageBoardDtoInput;

@Mapper
public interface MessageBoardMapper {

    SaveMessageBoardDtoInput PostMBListInputToSaveMessageBoardDtoInput(PostMBListInput postMBListInput);

    GetMBListDtoInput GetMBListInputToGetMBListDtoInput(GetMBListInput input);
}

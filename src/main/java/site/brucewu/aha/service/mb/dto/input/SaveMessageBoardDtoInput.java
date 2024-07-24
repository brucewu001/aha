package site.brucewu.aha.service.mb.dto.input;

import lombok.Data;
import site.brucewu.aha.service.mb.dto.MessageBoardDto;

import java.util.List;

@Data
public class SaveMessageBoardDtoInput {

    private List<MessageBoardDto> mbList;
}

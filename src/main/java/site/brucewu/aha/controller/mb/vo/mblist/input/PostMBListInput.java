package site.brucewu.aha.controller.mb.vo.mblist.input;

import lombok.Data;
import site.brucewu.aha.controller.mb.vo.mblist.MessageBoard;

import java.util.List;

@Data
public class PostMBListInput {

    private List<MessageBoard> mbList;
}

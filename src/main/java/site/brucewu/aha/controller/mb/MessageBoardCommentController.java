package site.brucewu.aha.controller.mb;

import org.springframework.web.bind.annotation.*;
import site.brucewu.aha.common.Result;
import site.brucewu.aha.controller.mb.vo.mbclist.*;

@RestController
@RequestMapping("/api/v1/aha/mb")
public class MessageBoardCommentController {

    @GetMapping("/ambc_list")
    Result<GetMBCListOutput> getAMBCList(GetMBCListInput input) {
        return null;
    }

    @PostMapping("/ambc_list")
    Result<PostMBCListOutput> postAMBCList(PostMBCListInput input) {
        return null;
    }

    @PutMapping("/ambc_list")
    Result<PutMBCListOutput> putAMBCList(PutMBCListInput input) {
        return null;
    }

    @DeleteMapping("/ambc_list")
    Result<DeleteMBCListOutput> deleteAMBCList(DeleteMBCListInput input){
        return null;
    }
}

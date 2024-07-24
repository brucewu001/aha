package site.brucewu.aha.controller.mb;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import site.brucewu.aha.common.Result;
import site.brucewu.aha.common.utils.RealIpUtil;
import site.brucewu.aha.controller.mb.vo.mbclist.*;
import site.brucewu.aha.controller.mb.vo.mblist.MessageBoard;
import site.brucewu.aha.controller.mb.vo.mblist.MessageBoardContent;
import site.brucewu.aha.controller.mb.vo.mblist.input.DeleteMBListInput;
import site.brucewu.aha.controller.mb.vo.mblist.input.GetMBListInput;
import site.brucewu.aha.controller.mb.vo.mblist.input.PostMBListInput;
import site.brucewu.aha.controller.mb.vo.mblist.input.PutMBListInput;
import site.brucewu.aha.controller.mb.vo.mblist.output.DeleteMBListOutput;
import site.brucewu.aha.controller.mb.vo.mblist.output.GetMBListOutput;
import site.brucewu.aha.controller.mb.vo.mblist.output.PutMBListOutput;
import site.brucewu.aha.mapping.mb.MessageBoardMapper;
import site.brucewu.aha.service.ip.IpToCityService;
import site.brucewu.aha.service.mb.AhaMessageBoardService;
import site.brucewu.aha.service.mb.dto.MessageBoardContentDto;
import site.brucewu.aha.service.mb.dto.MessageBoardDto;
import site.brucewu.aha.service.mb.dto.input.GetMBListDtoInput;
import site.brucewu.aha.service.mb.dto.input.SaveMessageBoardDtoInput;
import site.brucewu.aha.service.mb.dto.output.GetMBListDtoOutput;

import java.util.ArrayList;
import java.util.List;


@RestController
@CacheConfig(cacheNames = "resource.mb")
@RequestMapping("/api/v1/aha/external/mb")
@Slf4j
@CrossOrigin("*")
public class MessageBoardController {

    private final AhaMessageBoardService ahaMessageBoardService;
    private final IpToCityService ipToCityService;

    public MessageBoardController(AhaMessageBoardService ahaMessageBoardService, IpToCityService ipToCityService) {
        this.ahaMessageBoardService = ahaMessageBoardService;
        this.ipToCityService = ipToCityService;
    }

    @Cacheable(key = "'list_key_' + "
            + "#input.getKeyword() + '_' + "
            + "#input.getSortType() + '_' + "
            + "#input.getSortRule() + '_' + "
            + "#input.getSessionKey() + '_' + "
            + "#input.getCurrentPage() + '_' + "
            + "#input.getPageSize()"
    )
    @PostMapping("/get_amb_list")
    Result<GetMBListOutput> getAMBList(@RequestBody GetMBListInput input, HttpServletRequest request) {

        log.debug(request.getRemoteAddr());

        log.debug(input.toString());


        MessageBoardMapper mapper = Mappers.getMapper(MessageBoardMapper.class);

        GetMBListDtoInput getMBListDtoInput = mapper.GetMBListInputToGetMBListDtoInput(input);

        log.debug(getMBListDtoInput.toString());

        GetMBListDtoOutput getMBListDtoOutput = ahaMessageBoardService.getmbList(getMBListDtoInput);

        GetMBListOutput getMBListOutput = new GetMBListOutput();

        BeanUtils.copyProperties(getMBListDtoOutput, getMBListOutput);

        List<MessageBoardDto> mbDtoList = getMBListDtoOutput.getMbList();

        List<MessageBoard> mbList = new ArrayList<>();

        for (MessageBoardDto dto : mbDtoList) {

            MessageBoard messageBoard = new MessageBoard();

            BeanUtils.copyProperties(dto, messageBoard);

            List<MessageBoardContentDto> mbcDtoList = dto.getMbcList();

            List<MessageBoardContent> mbcList = new ArrayList<>();

            for(MessageBoardContentDto messageBoardContentDto : mbcDtoList) {

                MessageBoardContent messageBoardContent = new MessageBoardContent();

                BeanUtils.copyProperties(messageBoardContentDto, messageBoardContent);

                mbcList.add(messageBoardContent);
            }
            messageBoard.setMbcList(mbcList);

            mbList.add(messageBoard);
        }

        getMBListOutput.setMbList(mbList);

        return Result.ok(getMBListOutput);
    }

    @CacheEvict(allEntries = true)
    @PostMapping("/amb_list")
    Result<PostMBCListOutput> postAMBList(@RequestBody PostMBListInput input, HttpServletRequest request) {

        String realIp = RealIpUtil.getRealIp(request);

        log.debug("这个请求的realIp: {}", realIp);
        String city = ipToCityService.getCityByIp(realIp);

        log.debug("这个请求的ip对应的城市: {}", city);

        for(MessageBoard mb : input.getMbList()) {
            mb.setIp(city);
        }

        log.debug(input.toString());

        MessageBoardMapper mapper = Mappers.getMapper(MessageBoardMapper.class);

        SaveMessageBoardDtoInput saveMessageBoardDtoInput = mapper.PostMBListInputToSaveMessageBoardDtoInput(input);

        log.debug(saveMessageBoardDtoInput.toString());

        ahaMessageBoardService.saveMessageBoard(saveMessageBoardDtoInput);

        return Result.ok();
    }

    @PutMapping("/amb_list")
    Result<PutMBListOutput> putAMBList(PutMBListInput input) {
        return null;
    }

    @DeleteMapping("/amb_list")
    Result<DeleteMBListOutput> deleteAMBList(DeleteMBListInput input){
        return null;
    }


}

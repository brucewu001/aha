package site.brucewu.aha.service.mb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.brucewu.aha.common.utils.PageNumUtil;
import site.brucewu.aha.constant.SortRuleEnum;
import site.brucewu.aha.constant.SortTypeEnum;
import site.brucewu.aha.dao.mb.AhaMessageBoardContentRepository;
import site.brucewu.aha.dao.mb.AhaMessageBoardMainRepository;
import site.brucewu.aha.dao.mb.entity.AhaMessageBoardContent;
import site.brucewu.aha.dao.mb.entity.AhaMessageBoardMain;
import site.brucewu.aha.service.mb.dto.MessageBoardContentDto;
import site.brucewu.aha.service.mb.dto.MessageBoardDto;
import site.brucewu.aha.service.mb.dto.input.GetMBListDtoInput;
import site.brucewu.aha.service.mb.dto.input.SaveMessageBoardDtoInput;
import site.brucewu.aha.service.mb.dto.output.GetMBListDtoOutput;
import site.brucewu.aha.service.mb.dto.output.SaveMessageBoardDtoOutput;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AhaMessageBoardService {

    AhaMessageBoardMainRepository ahaMessageBoardMainRepository;
    AhaMessageBoardContentRepository ahaMessageBoardContentRepository;

    public AhaMessageBoardService(AhaMessageBoardMainRepository ahaMessageBoardMainRepository, AhaMessageBoardContentRepository ahaMessageBoardContentRepository) {
        this.ahaMessageBoardMainRepository = ahaMessageBoardMainRepository;
        this.ahaMessageBoardContentRepository = ahaMessageBoardContentRepository;
    }

    @Transactional
    public SaveMessageBoardDtoOutput saveMessageBoard(SaveMessageBoardDtoInput input) {
        log.debug("saveMessageBoard: executing...");
        List<MessageBoardDto> mbList = input.getMbList();
        if(mbList == null) return null;
        for (MessageBoardDto mb : mbList) {
            // 保存留言主表
            AhaMessageBoardMain ahaMessageBoardMain = new AhaMessageBoardMain();

            Long userId = mb.getUserId();
            if(userId!=null) {
                ahaMessageBoardMain.setUserId(userId);
            } else {
                ahaMessageBoardMain.setSessionKey(mb.getSessionKey());
            }
            BeanUtils.copyProperties(mb, ahaMessageBoardMain);
            log.debug(ahaMessageBoardMain.toString());
            AhaMessageBoardMain returnMessageBoardMain = ahaMessageBoardMainRepository.save(ahaMessageBoardMain);
            // 保存留言内容表
            List<MessageBoardContentDto> mbcList = mb.getMbcList();
            if(mbcList == null || mbcList.isEmpty()) {
                return null;
            }
            int a = 1/0;
            for (MessageBoardContentDto mbc: mbcList) {

                AhaMessageBoardContent ahaMessageBoardContent = new AhaMessageBoardContent();
                ahaMessageBoardContent.setContent(mbc.getContent());
                ahaMessageBoardContent.setContentType(mbc.getContentType());
                ahaMessageBoardContent.setAmbmId(returnMessageBoardMain.getId());

                ahaMessageBoardContentRepository.save(ahaMessageBoardContent);
            }
        }

        return new SaveMessageBoardDtoOutput();
    }

    public GetMBListDtoOutput getmbList(GetMBListDtoInput input) {

        // 排序规则
        String sortType = null;

        Sort.Direction sortRule = null;

        SortTypeEnum sortTypeEnum = SortTypeEnum.getEnum(input.getSortType());

        SortRuleEnum sortRuleEnum = SortRuleEnum.getEnum(input.getSortRule());

        if(SortTypeEnum.CREATE_TIME.equals(sortTypeEnum) || SortTypeEnum.LIKE_NUM.equals(sortTypeEnum)) {
            sortType = sortTypeEnum.getType();
        } else {
            sortType = "id";
        }

        if (SortRuleEnum.ASC.equals(sortRuleEnum) ) {
            sortRule = Sort.Direction.ASC;
        } else if (SortRuleEnum.DESC.equals(sortRuleEnum)) {
            sortRule = Sort.Direction.DESC;
        } else {
            sortRule = Sort.Direction.ASC;
        }
        
        log.debug("getmbList::sortType = {} sortRule = {}" , sortType, sortRule.name());

        Sort sort = Sort.by(sortRule, sortType);

        // 分页规则
        Pageable pageable = PageRequest.of(PageNumUtil.getCurrentPageNumInput(input.getCurrentPage()), input.getPageSize(), sort);

        Page<AhaMessageBoardMain> boardMains = null;


        if(input.getSessionKey() != null && !input.getSessionKey().isBlank()) {
            boardMains =  ahaMessageBoardMainRepository.findAllBySessionKey(input.getSessionKey(), pageable);
        } else {
            // 查询留言主表页
            boardMains = ahaMessageBoardMainRepository.findAll(pageable);

        }




        log.debug(boardMains.getContent().toString());

        // 装配留言主表页-> DTO
        GetMBListDtoOutput getMBListDtoOutput = new GetMBListDtoOutput();

        getMBListDtoOutput.setCurrentPage(PageNumUtil.getCurrentPageNumOutput(boardMains.getNumber()));

        getMBListDtoOutput.setPageSize(boardMains.getSize());

        getMBListDtoOutput.setTotalPage(boardMains.getTotalPages());

        getMBListDtoOutput.setCurrentPageSize(boardMains.getNumberOfElements());

        getMBListDtoOutput.setPageSize(input.getPageSize());

        // 获取留言主表列表
        List<AhaMessageBoardMain> contents = boardMains.getContent();

        List<MessageBoardDto> ambDtoList = new ArrayList<>();


        // 遍历留言主表，通过留言主表id查询留言内容表
        for(AhaMessageBoardMain main : contents) {

            // 装配留言主表->DTO
            MessageBoardDto mbDto = new MessageBoardDto();

            BeanUtils.copyProperties(main, mbDto);

            log.debug(mbDto.toString());

            // 通过留言主表id查询留言内容列表
            List<AhaMessageBoardContent> allByAmbmId = ahaMessageBoardContentRepository.findAllByAmbmId(main.getId());

            // 装配留言内容列表 -> DTO LIST
            List<MessageBoardContentDto> messageBoardContentDtos = new ArrayList<>();

            for (AhaMessageBoardContent content : allByAmbmId) {

                MessageBoardContentDto messageBoardContentDto = new MessageBoardContentDto();

                BeanUtils.copyProperties(content, messageBoardContentDto);

                messageBoardContentDtos.add(messageBoardContentDto);
            }

            mbDto.setMbcList(messageBoardContentDtos);


            ambDtoList.add(mbDto);
        }

        getMBListDtoOutput.setMbList(ambDtoList);

        return getMBListDtoOutput;
    }

}

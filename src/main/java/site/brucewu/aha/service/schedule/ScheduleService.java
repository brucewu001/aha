package site.brucewu.aha.service.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.brucewu.aha.dao.mb.AhaMessageBoardContentRepository;
import site.brucewu.aha.dao.mb.AhaMessageBoardMainRepository;
import site.brucewu.aha.dao.mb.entity.AhaMessageBoardContent;
import site.brucewu.aha.dao.mb.entity.AhaMessageBoardMain;
import site.brucewu.aha.dao.mb.specification.AhaMessageBoardSpecifications;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class ScheduleService {

    private final AhaMessageBoardMainRepository ahaMessageBoardMainRepository;

    private final AhaMessageBoardContentRepository ahaMessageBoardContentRepository;


    public ScheduleService(AhaMessageBoardMainRepository ahaMessageBoardMainRepository, AhaMessageBoardContentRepository ahaMessageBoardContentRepository) {
        this.ahaMessageBoardMainRepository = ahaMessageBoardMainRepository;
        this.ahaMessageBoardContentRepository = ahaMessageBoardContentRepository;
    }

    @Scheduled(cron = "0 0 1 * * ?")
    @Transactional
    public void scheduleMethod(){
        log.debug("开始清理脏数据-----------------------------");
        List<AhaMessageBoardMain> ahaMessageBoardMains = ahaMessageBoardMainRepository.findAllDirtyData();
        log.debug(ahaMessageBoardMains.toString());
        for(AhaMessageBoardMain main : ahaMessageBoardMains) {

            List<AhaMessageBoardContent> allByAmbmId = ahaMessageBoardContentRepository.findAllByAmbmId(main.getId());

            for(AhaMessageBoardContent ambc : allByAmbmId) {
                ahaMessageBoardContentRepository.deleteById(ambc.getId());
            }

            ahaMessageBoardMainRepository.deleteById(main.getId());

        }
    }
}

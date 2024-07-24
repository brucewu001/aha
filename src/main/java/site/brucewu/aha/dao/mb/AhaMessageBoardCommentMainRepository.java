package site.brucewu.aha.dao.mb;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import site.brucewu.aha.dao.mb.entity.AhaMessageBoardCommentMain;

@Repository
public interface AhaMessageBoardCommentMainRepository extends CrudRepository<AhaMessageBoardCommentMain, Long> {
}

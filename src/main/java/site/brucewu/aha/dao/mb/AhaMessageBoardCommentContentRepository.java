package site.brucewu.aha.dao.mb;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import site.brucewu.aha.dao.mb.entity.AhaMessageBoardCommentContent;


@Repository
public interface AhaMessageBoardCommentContentRepository extends CrudRepository<AhaMessageBoardCommentContent, Long> {
}

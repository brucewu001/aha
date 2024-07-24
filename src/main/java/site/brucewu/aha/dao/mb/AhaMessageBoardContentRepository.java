package site.brucewu.aha.dao.mb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import site.brucewu.aha.dao.mb.entity.AhaMessageBoardContent;

import java.util.List;

@Repository
public interface AhaMessageBoardContentRepository extends JpaRepository<AhaMessageBoardContent, Long> {

    List<AhaMessageBoardContent> findAllByAmbmId(Long ambmId);

}

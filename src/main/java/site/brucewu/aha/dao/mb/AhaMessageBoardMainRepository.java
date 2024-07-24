package site.brucewu.aha.dao.mb;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import site.brucewu.aha.dao.mb.entity.AhaMessageBoardMain;

import java.util.List;

@Repository
public interface AhaMessageBoardMainRepository extends JpaRepository<AhaMessageBoardMain, Long>, JpaSpecificationExecutor<AhaMessageBoardMain> {

    Page<AhaMessageBoardMain> findAllBySessionKey(String SessionKey, Pageable pageable);


    @Query(value = "select m.id, m.create_time, m.update_time, m.name, m.anonymous_enable, m.like_num, comment_num, m.ip, m.user_id, m.session_key, m.content_text from aha_message_board_main m " +
            "where (m.session_key is null or m.session_key = '') and (m.user_id is null or m.user_id = '')", nativeQuery = true)
    List<AhaMessageBoardMain> findAllDirtyData();

}

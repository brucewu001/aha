package site.brucewu.aha.dao.mb.specification;

import com.github.benmanes.caffeine.cache.RemovalListener;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import site.brucewu.aha.dao.mb.entity.AhaMessageBoardMain;

public class AhaMessageBoardSpecifications {

    public static Specification<AhaMessageBoardMain> hasDirtyData() {
        Specification<AhaMessageBoardMain> nullSessionKey  = ((root, query, criteriaBuilder) -> criteriaBuilder.isEmpty(root.get("sessionKey")));
//        Specification<AhaMessageBoardMain> blankSessionKey = ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("sessionKey"), ""));
//        Specification<AhaMessageBoardMain> nullUserId = ((root, query, criteriaBuilder) -> criteriaBuilder.isEmpty(root.get("userId")));
//        Specification<AhaMessageBoardMain> blankUserId = ((root, query, criteriaBuilder) -> criteriaBuilder.isEmpty(root.get("userId"), ""));

        return nullSessionKey;
    }

}

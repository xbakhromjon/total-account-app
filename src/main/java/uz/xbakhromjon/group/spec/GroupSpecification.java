package uz.xbakhromjon.group.spec;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import uz.xbakhromjon.group.entity.GroupJpaEntity;

public class GroupSpecification implements Specification<GroupJpaEntity> {
    @Override
    public Predicate toPredicate(Root<GroupJpaEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return null;
    }
}

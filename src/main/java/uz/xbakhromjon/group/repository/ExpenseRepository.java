package uz.xbakhromjon.group.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uz.xbakhromjon.group.entity.ExpenseJpaEntity;

@Repository
@Transactional
public interface ExpenseRepository extends JpaRepository<ExpenseJpaEntity, Long> {
}

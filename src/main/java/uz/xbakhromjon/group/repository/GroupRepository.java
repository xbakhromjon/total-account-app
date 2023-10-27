package uz.xbakhromjon.group.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uz.xbakhromjon.group.entity.GroupJpaEntity;

@Repository
@Transactional
public interface GroupRepository extends JpaRepository<GroupJpaEntity, Long> {
}

package uz.xbakhromjon.group.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uz.xbakhromjon.group.entity.GroupJpaEntity;

import java.util.Optional;

@Repository
@Transactional
public interface GroupRepository extends JpaRepository<GroupJpaEntity, Long>, JpaSpecificationExecutor<GroupJpaEntity> {
    @EntityGraph(attributePaths = {"people"})
    Optional<GroupJpaEntity> findWithPeopleById(Long id);

    Optional<GroupJpaEntity> findByIdAndOwnerId(Long id, Long ownerId);

    @Query(nativeQuery = true, value = """
            exists()
            """)
    boolean existsByIdAndNotExistsByIdAndOwnerId(Long id, Long ownerId);

    void deleteByIdAndOwnerId(Long id, Long ownerId);
}

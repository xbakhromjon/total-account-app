package uz.xbakhromjon.group.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uz.xbakhromjon.group.entity.PersonJpaEntity;

import java.util.List;

@Repository
@Transactional
public interface PersonRepository extends JpaRepository<PersonJpaEntity, Long> {

    @Modifying
    @Query(nativeQuery = true, value = "update users set given_money = given_money + :amount where id = :id")
    void addMoney(Long id, float amount);

    List<PersonJpaEntity> findAllByGroupId(Long groupId);

    boolean existsByIdAndGroupId(Long id, Long groupId);

    Integer countByGroupId(Long groupId);

    @Query(nativeQuery = true, value = """
            select sum(given_money) from person where not is_deleted and id = :groupId group by group_id
            """)
    float getTotalMoneyOfGroup(@Param(value = "groupId") Long groupId);
}

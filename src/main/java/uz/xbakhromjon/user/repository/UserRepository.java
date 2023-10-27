package uz.xbakhromjon.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uz.xbakhromjon.user.entity.UserJpaEntity;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<UserJpaEntity, Long> {
}

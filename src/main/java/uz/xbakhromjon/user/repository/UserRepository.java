package uz.xbakhromjon.user.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uz.xbakhromjon.user.entity.UserJpaEntity;

import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<UserJpaEntity, Long> {
    Optional<UserJpaEntity> findOneByUsername(String username);
    @EntityGraph(attributePaths = "role.privileges")
    Optional<UserJpaEntity> findOneWithAuthoritiesByUsername(String username);
    @EntityGraph(attributePaths = "role.privileges")
    Optional<UserJpaEntity> findOneWithAuthoritiesByUsernameIgnoreCase(String email);

    boolean existsByUsername(String username);

}

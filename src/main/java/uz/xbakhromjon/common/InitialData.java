package uz.xbakhromjon.common;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uz.xbakhromjon.user.entity.UserJpaEntity;
import uz.xbakhromjon.user.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class InitialData implements CommandLineRunner {
    private final UserRepository userRepository;

    @Value(value = "${spring.jpa.hibernate.ddl-auto}")
    private String ddl;

    @Override
    public void run(String... args) throws Exception {
        if (ddl.contains("create")) {
            userRepository.save(new UserJpaEntity("xbakhromjon", "$2a$12$lhpAGGMJzqPP1AOHqbYGL.ypq64USxh9tD0og4uKW.twuFLdyLV9q", "Bahromjon", "Xasanboyev"));
        }
    }
}

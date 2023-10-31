package uz.xbakhromjon.user.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import uz.xbakhromjon.auth.security.ERole;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * An authority (a security role) used by Spring Security.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "user_role")
public class UserRole implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ERole name;


    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> privileges = new HashSet<>();

    public UserRole(ERole name) {
        this.name = name;
        this.privileges = name.getDefaultAuthorities();
    }
}

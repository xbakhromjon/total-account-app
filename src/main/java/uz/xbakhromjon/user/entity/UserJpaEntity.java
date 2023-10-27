package uz.xbakhromjon.user.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "users")
@SQLDelete(sql = "update users set is_deleted = true where id = ?")
@Where(clause = "not is_deleted")
public class UserJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_generator")
    @SequenceGenerator(name = "users_generator", sequenceName = "users_seq")
    private Long id;

    // TODO: 10/27/2023 create unique index
    @Column(name = "username", nullable = false, columnDefinition = "varchar(20)")
    private String username;

    @ToString.Exclude
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "is_deleted")
    private boolean isDeleted;
}

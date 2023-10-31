package uz.xbakhromjon.user.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @ToString.Exclude
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(nullable = false)
    private UserRole role;


    public UserJpaEntity(String username, String password, String firstname, String lastname, UserRole role) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.role = role;
    }
}

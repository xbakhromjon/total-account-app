package uz.xbakhromjon.group.entity;

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
@Table(name = "person")
@SQLDelete(sql = "update group set is_deleted = true where id = ?")
@Where(clause = "not is_deleted")
public class PersonJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_generator")
    @SequenceGenerator(name = "person_generator", sequenceName = "person_seq")
    private Long id;

    @Column(name = "nickname", nullable = false, columnDefinition = "varchar(20)")
    private String nickname;

    @Column(name = "given_money")
    private float givenMoney;

    private transient float takenMoney;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = false)
    private GroupJpaEntity group;

}

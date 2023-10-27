package uz.xbakhromjon.group.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import uz.xbakhromjon.user.entity.UserJpaEntity;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "groups")
@SQLDelete(sql = "update groups set is_deleted = true where id = ?")
@Where(clause = "not is_deleted")
public class GroupJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "groups_generator")
    @SequenceGenerator(name = "groups_generator", sequenceName = "groups_seq")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private UserJpaEntity owner;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "group")
    private Set<PersonJpaEntity> people;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "group")
    private Set<ExpenseJpaEntity> expenses;
}

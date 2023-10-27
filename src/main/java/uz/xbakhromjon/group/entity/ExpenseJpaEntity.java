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
@Table(name = "expense")
@SQLDelete(sql = "update expense set is_deleted = true where id = ?")
@Where(clause = "not is_deleted")
public class ExpenseJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "expense_generator")
    @SequenceGenerator(name = "expense_generator", sequenceName = "expense_seq")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "amount", nullable = false)
    private Float amount;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = false)
    private GroupJpaEntity group;
}

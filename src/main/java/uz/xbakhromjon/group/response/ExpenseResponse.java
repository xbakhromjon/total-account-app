package uz.xbakhromjon.group.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ExpenseResponse {
    private Long id;
    private String name;
    private Float amount;
}

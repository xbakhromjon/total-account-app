package uz.xbakhromjon.group.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class GroupResponse {
    private Long id;
    private String name;
    private Integer peopleCount;
    private Float totalMoney;
}

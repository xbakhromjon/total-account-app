package uz.xbakhromjon.group.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class PersonResponse {
    private String nickname;
    private Float givenMoney;
}

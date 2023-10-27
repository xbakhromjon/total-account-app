package uz.xbakhromjon.group.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class EntitledPersonResponse {
    private Long id;
    private String nickname;
    private Float givenMoney;
    private Float takenMoney;
}



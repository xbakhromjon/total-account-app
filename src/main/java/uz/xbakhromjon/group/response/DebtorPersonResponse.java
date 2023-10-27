package uz.xbakhromjon.group.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class DebtorPersonResponse {
    private Long id;
    private String nickname;
    private Float givenMoney;
    private List<EntitledPersonResponse> entitledPeople;
}



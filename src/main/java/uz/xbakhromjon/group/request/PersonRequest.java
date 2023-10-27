package uz.xbakhromjon.group.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class PersonRequest {
    @NotBlank
    private String nickname;

    private float givenMoney;
}

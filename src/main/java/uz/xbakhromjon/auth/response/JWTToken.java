package uz.xbakhromjon.auth.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class JWTToken {
    private String idToken;
}

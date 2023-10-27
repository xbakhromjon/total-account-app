package uz.xbakhromjon.user.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class UserResponse {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
}

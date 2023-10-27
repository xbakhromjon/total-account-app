package uz.xbakhromjon.user.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class UserChangePasswordRequest {
    private String oldPassword;
    private String newPassword;
}

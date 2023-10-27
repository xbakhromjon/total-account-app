package uz.xbakhromjon.group.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class GroupRequest {
    @NotBlank
    private String name;
}

package uz.xbakhromjon.group.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ExpenseRequest {
    @NotBlank
    private String name;

    @NotNull
    @Positive
    private Float amount;
}

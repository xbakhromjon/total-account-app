package uz.xbakhromjon.common.exception;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class ProblemDetail {
    private int status;
    private String type;
    private String details;
    private Map<String, Object> properties;
    private LocalDateTime timestamp;
    private String path;
}

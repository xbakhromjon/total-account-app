package uz.xbakhromjon.common.exception;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.io.IOException;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class UserErrorResponseException extends IOException {
    private HttpStatus status;
    private String details;

    public UserErrorResponseException(HttpStatus status, String details) {
        super(details);
        this.status = status;
    }
}

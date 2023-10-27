package uz.xbakhromjon.common.exception;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class ClientErrorResponseException extends RuntimeException {
    private HttpStatus status;

    public ClientErrorResponseException(HttpStatus status, String details) {
        super(details);
        this.status = status;
    }
}

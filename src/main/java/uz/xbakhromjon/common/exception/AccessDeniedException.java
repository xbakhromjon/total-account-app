package uz.xbakhromjon.common.exception;

import org.springframework.http.HttpStatus;

public class AccessDeniedException extends ClientErrorResponseException {

    public AccessDeniedException(String details) {
        super(HttpStatus.FORBIDDEN, details);
    }
}

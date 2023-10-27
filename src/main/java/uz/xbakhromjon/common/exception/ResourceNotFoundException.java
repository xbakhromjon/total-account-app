package uz.xbakhromjon.common.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends ClientErrorResponseException {

    public ResourceNotFoundException(String details) {
        super(HttpStatus.BAD_REQUEST, details);
    }
}

package uz.xbakhromjon.common.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends ClientErrorResponseException {

    public BadRequestException(String details) {
        super(HttpStatus.BAD_REQUEST, details);
    }
}

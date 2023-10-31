package uz.xbakhromjon.common.exception;

import org.springframework.http.HttpStatus;
import uz.xbakhromjon.common.constants.ErrorMessages;

public class InvalidPasswordException extends UserErrorResponseException {

    public InvalidPasswordException() {
        super(HttpStatus.BAD_REQUEST, ErrorMessages.INVALID_PASSWORD);
    }
}

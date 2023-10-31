package uz.xbakhromjon.common.exception;

import org.springframework.http.HttpStatus;
import uz.xbakhromjon.common.constants.ErrorMessages;

public class UsernameAlreadyUsedException extends UserErrorResponseException {


    public UsernameAlreadyUsedException() {
        super(HttpStatus.BAD_REQUEST, ErrorMessages.USERNAME_ALREADY_TAKEN);
    }
}

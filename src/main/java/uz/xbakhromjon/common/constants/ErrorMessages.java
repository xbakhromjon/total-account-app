package uz.xbakhromjon.common.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ErrorMessages {

    public static final String X_RESOURCE_NOT_FOUND = "%s resource not found";
    public static final String INVALID_X_ID = "invalid %s id";
    public static final String INVALID_PASSWORD = "Incorrect password";
    public static final String USERNAME_ALREADY_TAKEN = "Login name already used!";
    public static final String UNAUTHORIZED = "unauthorized";
    public static final String USERNAME_OR_PASSWORD_INVALID = "username/password is incorrect";
}

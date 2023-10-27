package uz.xbakhromjon.user.service;


import uz.xbakhromjon.auth.request.SignupRequest;
import uz.xbakhromjon.user.request.UserChangePasswordRequest;
import uz.xbakhromjon.user.request.UserUpdateRequest;
import uz.xbakhromjon.user.response.UserResponse;

public interface UserService {
    Long create(SignupRequest request);

    Long update(Long id, UserUpdateRequest request);

    void delete(Long id);

    UserResponse getOne(Long id);

    void changePassword(Long id, UserChangePasswordRequest request);
}

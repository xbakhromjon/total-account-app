package uz.xbakhromjon.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.xbakhromjon.auth.request.SignupRequest;
import uz.xbakhromjon.user.request.UserChangePasswordRequest;
import uz.xbakhromjon.user.request.UserUpdateRequest;
import uz.xbakhromjon.user.response.UserResponse;
import uz.xbakhromjon.user.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Override
    public Long create(SignupRequest request) {
        return null;
    }

    @Override
    public Long update(Long id, UserUpdateRequest request) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public UserResponse getOne(Long id) {
        return null;
    }

    @Override
    public void changePassword(Long id, UserChangePasswordRequest request) {

    }
}

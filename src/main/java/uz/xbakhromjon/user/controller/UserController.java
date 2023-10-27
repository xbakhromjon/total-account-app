package uz.xbakhromjon.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.xbakhromjon.common.security.SecurityUtils;
import uz.xbakhromjon.user.request.UserChangePasswordRequest;
import uz.xbakhromjon.user.request.UserUpdateRequest;
import uz.xbakhromjon.user.response.UserResponse;
import uz.xbakhromjon.user.service.UserService;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PutMapping()
    public ResponseEntity<Long> update(@RequestBody @Valid UserUpdateRequest request) {
        Long id = userService.update(SecurityUtils.getCurrentUserId(), request);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping()
    public ResponseEntity<Void> delete() {
        userService.delete(SecurityUtils.getCurrentUserId());
        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    public ResponseEntity<UserResponse> getAccount() {
        UserResponse response = userService.getOne(SecurityUtils.getCurrentUserId());
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/password")
    public ResponseEntity<Void> changePassword(@RequestBody @Valid UserChangePasswordRequest request) {
        userService.changePassword(SecurityUtils.getCurrentUserId(), request);
        return ResponseEntity.noContent().build();
    }
}

package com.sio2whocodes.wibor.controller;

import com.sio2whocodes.wibor.controller.dto.UserResponseDto;
import com.sio2whocodes.wibor.service.UserService;
import com.sio2whocodes.wibor.util.ResponseCode;
import com.sio2whocodes.wibor.util.ResponseData;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    @GetMapping("{userId}")
    public ResponseEntity<ResponseData<UserResponseDto>> readUser(
        @PathVariable @NotNull Long userId) {
        UserResponseDto data = userService.readUser(userId);
        return ResponseData.toResponseEntity(ResponseCode.READ_USER_SUCCESS, data);
    }

}

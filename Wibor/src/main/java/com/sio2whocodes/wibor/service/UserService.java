package com.sio2whocodes.wibor.service;

import com.sio2whocodes.wibor.controller.dto.UserResponseDto;
import com.sio2whocodes.wibor.entity.User;
import com.sio2whocodes.wibor.exception.ResourceNotFoundException;
import com.sio2whocodes.wibor.repository.UserRepository;
import com.sio2whocodes.wibor.util.ResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDto readUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException(ResponseCode.USER_NOT_FOUND));
        return UserResponseDto.of(user);
    }


}

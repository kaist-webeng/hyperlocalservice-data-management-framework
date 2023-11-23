package com.sio2whocodes.wibor.controller.dto;


import com.sio2whocodes.wibor.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class UserResponseDto {

    private final Long id;
    private final String name;
    private final Boolean isAuthenticated;
    private final LocalDate authenticatedDate;

    public static UserResponseDto of(User user){
        return UserResponseDto.builder()
            .id(user.getId())
            .name(user.getName())
            .isAuthenticated(user.getIsAuthenticated())
            .authenticatedDate(user.getAuthenticatedDate())
            .build();
    }

}

package com.sio2whocodes.registry.controller;

import com.sio2whocodes.registry.entity.Registry;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegistryResponseDto {
    private final String locationCode;
    private final String entity;
    private final String DBUrl;
    private final String DBUsername;
    private final String DBPassword;
    private final String DBId;

    public static RegistryResponseDto of(Registry registry){
        return RegistryResponseDto.builder()
            .locationCode(registry.getLocationCode())
            .entity(registry.getEntity())
            .DBUrl(registry.getDBUrl())
            .DBUsername(registry.getDBUsername())
            .DBPassword(registry.getDBPassword())
            .DBId(registry.getDBId())
            .build();
    }
}

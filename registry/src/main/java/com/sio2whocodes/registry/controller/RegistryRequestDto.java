package com.sio2whocodes.registry.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RegistryRequestDto {
    private String locationCode;
    private String entity;
    private String dbUrl;
    private String dbUsername;
    private String dbPassword;
    private String dbId;
}

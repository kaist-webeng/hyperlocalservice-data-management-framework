package com.sio2whocodes.registry.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Registry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String entity;

    @NotNull
    private String locationCode;

    @NotNull
    @Column(name = "DB_url")
    private String DBUrl;

    @NotNull
    @Column(name = "DB_username")
    private String DBUsername;

    @NotNull
    @Column(name = "DB_password")
    private String DBPassword;

    @NotNull
    @Column(name = "DB_id")
    private String DBId;
}

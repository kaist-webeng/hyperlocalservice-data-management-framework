package com.sio2whocodes.wibor.entity;

import com.sio2whocodes.wibor.annotation.StaticCloud;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@StaticCloud
@Entity
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @Column(name = "is_authenticated")
    private Boolean isAuthenticated;

    @Column(name = "authenticated_date")
    private LocalDate authenticatedDate;

    private String email;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

}

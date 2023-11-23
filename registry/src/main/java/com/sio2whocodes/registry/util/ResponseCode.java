package com.sio2whocodes.registry.util;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ResponseCode {

    READ_DATABASE_SUCCESS(OK, "데이터베이스 위치 조회 성공"),
    SAVE_DATABASE_SUCCESS(CREATED, "데이터베이스 위치 저장 성공"),
    DATABASE_NOT_FOUND(NOT_FOUND, "해당 데이터 위치를 찾을 수 없습니다");

    private final HttpStatus httpStatus;
    private final String detail;

}
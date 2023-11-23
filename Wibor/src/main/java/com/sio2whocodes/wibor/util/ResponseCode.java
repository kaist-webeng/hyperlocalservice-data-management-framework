package com.sio2whocodes.wibor.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
@Getter
@AllArgsConstructor
public enum ResponseCode {

    READ_USER_SUCCESS(OK, "유저 정보 조회 성공"),
    READ_POST_SUCCESS(OK, "게시글 조회 성공"),
    READ_POSTS_SUCCESS(OK, "게시글 목록 조회 성공"),


    /* 201 CREATED */

    CREATE_POST_SUCCESS(CREATED, "게시글 생성 성공"),

    /* 404 NOT_FOUND : Resource 를 찾을 수 없음 */
    USER_NOT_FOUND(NOT_FOUND, "유저 정보를 찾을 수 없습니다"),
    POST_NOT_FOUND(NOT_FOUND, "해당 게시글을 찾을 수 없습니다"),

    DATASOURCE_NOT_FOUND(NOT_FOUND, "해당 데이터소스를 찾을 수 없습니다");

    private final HttpStatus httpStatus;
    private final String detail;

}
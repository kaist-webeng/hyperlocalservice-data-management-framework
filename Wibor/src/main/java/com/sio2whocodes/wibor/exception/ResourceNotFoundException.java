package com.sio2whocodes.wibor.exception;

import com.sio2whocodes.wibor.util.ResponseCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ResourceNotFoundException extends RuntimeException {

    private final ResponseCode responseCode;
}
package com.sio2whocodes.registry.exception;

import com.sio2whocodes.registry.util.ResponseCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ResourceNotFoundException extends RuntimeException {
    private final ResponseCode responseCode;
}
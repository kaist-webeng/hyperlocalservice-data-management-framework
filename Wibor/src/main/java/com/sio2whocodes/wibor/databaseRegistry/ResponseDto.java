package com.sio2whocodes.wibor.databaseRegistry;

import lombok.ToString;

@ToString
public class ResponseDto<T> {
    public String timestamp;
    public String code;
    public String status;
    public String detail;
    public T data;
}

package com.sio2whocodes.wibor.databaseRegistry;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RegistryDto {
    public String entity;
    public String locationCode;
    public String dburl;
    public String dbusername;
    public String dbpassword;
    public String dbid;
}

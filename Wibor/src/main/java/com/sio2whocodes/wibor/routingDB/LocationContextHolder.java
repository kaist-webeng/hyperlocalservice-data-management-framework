package com.sio2whocodes.wibor.routingDB;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LocationContextHolder {
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();
    public static void setLocationContext(String locationEnum) {
        threadLocal.set(locationEnum);
    }

    public static String getLocationContext(){
        return threadLocal.get();
    }

    public static void clearLocationContext(){
        threadLocal.remove();
    }
}
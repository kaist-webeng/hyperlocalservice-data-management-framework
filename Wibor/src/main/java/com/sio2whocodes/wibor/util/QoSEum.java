package com.sio2whocodes.wibor.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum QoSEum {
    RESOURCE_AVAILABLITY("resource_availability"),
    NETWORK_TRAFFIC("network_traffic"),
    PROCESSOR_POWER("processor_power"),
    PERFORMANCE("performance"),
    MEMORY_SPACE("memory_space");

    private final String name;
}

package com.sio2whocodes.registry.service;

import com.sio2whocodes.registry.util.ResponseCode;
import com.sio2whocodes.registry.controller.RegistryRequestDto;
import com.sio2whocodes.registry.controller.RegistryResponseDto;
import com.sio2whocodes.registry.entity.Registry;
import com.sio2whocodes.registry.exception.ResourceNotFoundException;
import com.sio2whocodes.registry.repository.RegistryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class RegistryService {

    private final RegistryRepository registryRepository;

    public RegistryResponseDto search(String entity, String locationCode) {
        Registry registry = registryRepository.findByEntityAndLocationCode(entity,
            locationCode).orElseThrow(() -> new ResourceNotFoundException(
            ResponseCode.DATABASE_NOT_FOUND));

        return RegistryResponseDto.of(registry);
    }

    public void save(RegistryRequestDto requestDto) {
        Registry registry = Registry.builder()
            .entity(requestDto.getEntity())
            .locationCode(requestDto.getLocationCode())
            .DBUrl(requestDto.getDbUrl())
            .DBUsername(requestDto.getDbUsername())
            .DBPassword(requestDto.getDbPassword())
            .DBId(requestDto.getDbId())
            .build();

        registryRepository.save(registry);
    }
}

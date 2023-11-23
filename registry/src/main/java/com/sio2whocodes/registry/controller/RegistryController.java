package com.sio2whocodes.registry.controller;

import com.sio2whocodes.registry.util.ResponseCode;
import com.sio2whocodes.registry.util.ResponseData;
import com.sio2whocodes.registry.service.RegistryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("registry")
public class RegistryController {

    private final RegistryService registryService;

    @GetMapping("search")
    public ResponseEntity<ResponseData<RegistryResponseDto>> searchDatabase(
        @RequestParam(value = "entity") @NotNull String entity,
        @RequestParam(value = "locationCode") @NotNull String locationCode) {
        RegistryResponseDto data = registryService.search(entity, locationCode);
        return ResponseData.toResponseEntity(ResponseCode.READ_DATABASE_SUCCESS, data);
    }

    @PostMapping("save")
    public ResponseEntity<ResponseData> save(@Valid @RequestBody RegistryRequestDto request) {
        registryService.save(request);
        return ResponseData.toResponseEntity(ResponseCode.SAVE_DATABASE_SUCCESS);
    }
}

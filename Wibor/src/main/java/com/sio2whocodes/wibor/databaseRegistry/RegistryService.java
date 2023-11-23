package com.sio2whocodes.wibor.databaseRegistry;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sio2whocodes.wibor.exception.ResourceNotFoundException;
import com.sio2whocodes.wibor.util.ResponseCode;
import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class RegistryService {
    public RegistryDto getDatabase(String entity, String locationCode){
        URI uri = UriComponentsBuilder
            .fromUriString("http://localhost:8090")
            .path("/registry/search")
            .queryParam("entity", entity)
            .queryParam("locationCode", locationCode)
            .encode()
            .build()
            .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);

        RegistryDto registry;
        ObjectMapper mapper = new ObjectMapper();
        try {
            ResponseDto<RegistryDto> dto = mapper.readValue(responseEntity.getBody(), ResponseDto.class);
            registry = mapper.convertValue(dto.data, RegistryDto.class);
            return registry;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException(ResponseCode.DATASOURCE_NOT_FOUND);
        }
    }
}

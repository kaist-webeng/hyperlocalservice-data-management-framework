package com.sio2whocodes.registry.repository;

import com.sio2whocodes.registry.entity.Registry;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistryRepository extends JpaRepository<Registry, Long> {
    Optional<Registry> findByEntityAndLocationCode(String entity, String locationCode);
}

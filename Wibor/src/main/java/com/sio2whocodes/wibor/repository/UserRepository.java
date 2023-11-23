package com.sio2whocodes.wibor.repository;

import com.sio2whocodes.wibor.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

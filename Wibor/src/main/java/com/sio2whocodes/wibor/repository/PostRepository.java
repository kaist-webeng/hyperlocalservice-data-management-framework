package com.sio2whocodes.wibor.repository;

import com.sio2whocodes.wibor.entity.Post;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAll();
}

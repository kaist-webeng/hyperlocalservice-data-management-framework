package com.sio2whocodes.wibor.service;

import com.sio2whocodes.wibor.controller.dto.PostRequestDto;
import com.sio2whocodes.wibor.controller.dto.PostResponseDto;
import com.sio2whocodes.wibor.entity.Post;
import com.sio2whocodes.wibor.entity.User;
import com.sio2whocodes.wibor.exception.ResourceNotFoundException;
import com.sio2whocodes.wibor.repository.PostRepository;
import com.sio2whocodes.wibor.repository.UserRepository;
import com.sio2whocodes.wibor.util.ResponseCode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    public PostResponseDto readPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException(
            ResponseCode.POST_NOT_FOUND));

        return PostResponseDto.of(post);
    }

    public List<PostResponseDto> readPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(PostResponseDto::of).collect(Collectors.toList());
    }

    public PostResponseDto createPost(PostRequestDto request) {
        User user = userRepository.findById(request.getUserId())
            .orElseThrow(() -> new ResourceNotFoundException(ResponseCode.USER_NOT_FOUND));

        Post post = Post.builder()
            .user(user)
            .title(request.getTitle())
            .content(request.getContent())
            .createdAt(LocalDateTime.now())
            .latitude(request.getLatitude())
            .longitude(request.getLongitude())
            .build();

        postRepository.save(post);

        return PostResponseDto.of(post);
    }

}

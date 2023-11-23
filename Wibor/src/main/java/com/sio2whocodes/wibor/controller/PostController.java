package com.sio2whocodes.wibor.controller;

import com.sio2whocodes.wibor.controller.dto.PostRequestDto;
import com.sio2whocodes.wibor.controller.dto.PostResponseDto;
import com.sio2whocodes.wibor.service.PostService;
import com.sio2whocodes.wibor.util.ResponseCode;
import com.sio2whocodes.wibor.util.ResponseData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("posts")
public class PostController {

    private final PostService postService;

    @GetMapping("{postId}")
    public ResponseEntity<ResponseData<PostResponseDto>> readPost(
        @PathVariable @NotNull Long postId) {
        PostResponseDto data = postService.readPost(postId);
        return ResponseData.toResponseEntity(ResponseCode.READ_POST_SUCCESS, data);
    }

    @GetMapping("")
    public ResponseEntity<ResponseData<List<PostResponseDto>>> readPosts() {
        List<PostResponseDto> data = postService.readPosts();
        return ResponseData.toResponseEntity(ResponseCode.READ_POST_SUCCESS, data);
    }

    @PostMapping("")
    public ResponseEntity<ResponseData<PostResponseDto>> createPost(@Valid PostRequestDto request) {
        PostResponseDto data = postService.createPost(request);
        return ResponseData.toResponseEntity(ResponseCode.CREATE_POST_SUCCESS, data);
    }
}

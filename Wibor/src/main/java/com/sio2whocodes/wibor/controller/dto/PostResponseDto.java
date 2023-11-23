package com.sio2whocodes.wibor.controller.dto;

import com.sio2whocodes.wibor.entity.Post;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PostResponseDto {
    private final Long postId;
    private final Long userId;
    private final String title;
    private final String content;
    private final BigDecimal latitude;
    private final BigDecimal longitude;

    public static PostResponseDto of(Post post){
        return PostResponseDto.builder()
            .postId(post.getId())
            .userId(post.getUser().getId())
            .title(post.getTitle())
            .content(post.getContent())
            .latitude(post.getLatitude())
            .longitude(post.getLongitude())
            .build();
    }
}

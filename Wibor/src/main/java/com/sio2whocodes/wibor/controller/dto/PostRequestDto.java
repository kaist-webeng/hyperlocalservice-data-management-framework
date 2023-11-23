package com.sio2whocodes.wibor.controller.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostRequestDto {

    private Long userId;

    private String title;

    private String content;

    private BigDecimal latitude;

    private BigDecimal longitude;

}

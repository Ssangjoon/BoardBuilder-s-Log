package com.boardbuilderslog.bulletin_board.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigInteger;
import java.time.LocalDate;

@Getter
@Builder
public class PostSelectOneResponse {
    private Long id;
    private String title;
    private String content;
    private LocalDate startDate;
    private LocalDate endDate;
    private String thumbnailUrl;
}

package com.boardbuilderslog.bulletin_board.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class PostSelectOneResponse {
    private String title;
    private String content;
    private LocalDate startDate;
    private LocalDate endDate;
}

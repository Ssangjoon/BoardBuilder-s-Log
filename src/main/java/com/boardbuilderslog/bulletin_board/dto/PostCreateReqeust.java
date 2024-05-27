package com.boardbuilderslog.bulletin_board.dto;

import com.boardbuilderslog.bulletin_board.entity.Post;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class PostCreateReqeust {

    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotBlank
    private Boolean isPublic;
    private LocalDate startDate;
    private LocalDate endDate;
    public Post toEntity(){
        return Post.builder()
                .title(title)
                .content(content)
                .isPublic(isPublic)
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }
}

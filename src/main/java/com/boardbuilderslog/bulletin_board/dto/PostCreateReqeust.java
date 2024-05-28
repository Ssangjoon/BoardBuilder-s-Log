package com.boardbuilderslog.bulletin_board.dto;

import com.boardbuilderslog.bulletin_board.entity.Post;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class PostCreateReqeust {

    @NotBlank(message = "{title.notBlank}")
    private String title;

    @NotBlank(message = "{content.notBlank}")
    private String content;

    @NotNull(message = "{isPublic.notNull}")
    private Boolean isPublic;

    @NotNull(message = "{startDate.notNull}")
    private LocalDate startDate;

    @NotNull(message = "{endDate.notNull}")
    private LocalDate endDate;
    @AssertTrue(message = "지정일을 확인하십시오.")
    public boolean isStartDateBeforeEndDate(){
        if (startDate == null || endDate == null) {
            return true;
        }
        return !startDate.isAfter(endDate);
    }
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

package com.boardbuilderslog.bulletin_board.dto;

import com.boardbuilderslog.bulletin_board.entity.Post;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Getter
@Setter
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

    private MultipartFile thumbnail;
    private String thumbnailUrl;

    @AssertTrue(message = "{startDateBeforeEndDate}")
    public boolean isStartDateBeforeEndDate() {
        if (startDate == null || endDate == null) {
            return true; // 기본적으로 null 값은 다른 애노테이션에서 처리합니다.
        }
        return !startDate.isAfter(endDate);
    }

    public Post toEntity() {
        return Post.builder()
                .title(title)
                .content(content)
                .isPublic(isPublic)
                .thumbnailUrl(thumbnailUrl)
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }
}

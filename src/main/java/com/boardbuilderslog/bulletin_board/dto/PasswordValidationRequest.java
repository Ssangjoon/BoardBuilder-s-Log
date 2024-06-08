package com.boardbuilderslog.bulletin_board.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class PasswordValidationRequest {
    @NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
    @Size(min = 8, message = "비밀번호는 최소 8자 이상이어야 합니다.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[A-Za-z])(?=.*[@#$%^&+=!]).{8,}$",
            message = "비밀번호는 하나 이상의 영문자, 숫자, 특수 문자를 포함해야 하며 최소 8자 이상이어야 합니다.")
    private String password;
}

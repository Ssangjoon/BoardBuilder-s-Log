package com.boardbuilderslog.bulletin_board.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
}

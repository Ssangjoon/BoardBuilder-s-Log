package com.boardbuilderslog.bulletin_board.controller;

import com.boardbuilderslog.bulletin_board.dto.PasswordValidationRequest;
import com.boardbuilderslog.bulletin_board.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/check-username")
    public Boolean checkUsername(
            @RequestParam @Size(min = 3, max = 20, message = "{username.size}")
            @Pattern(regexp = "^[a-zA-Z0-9_-]+$", message = "{username.pattern}")
            String username) {

        userService.checkUsername(username);
        return true;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/validatePassword")
    public Map<String, Object> validatePassword(@Valid @RequestBody PasswordValidationRequest request, BindingResult result) throws MethodArgumentNotValidException {
        if (result.hasErrors()) {
            log.info("errors={}", result);
            throw new MethodArgumentNotValidException(null, result);
        }
        Map<String, Object> response = new HashMap<>();
        response.put("valid", true);
        return response;
    }
}

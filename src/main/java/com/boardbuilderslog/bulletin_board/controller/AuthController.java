package com.boardbuilderslog.bulletin_board.controller;

import com.boardbuilderslog.bulletin_board.dto.LoginRequest;
import com.boardbuilderslog.bulletin_board.dto.PasswordValidationRequest;
import com.boardbuilderslog.bulletin_board.dto.UserRegistrationRequest;
import com.boardbuilderslog.bulletin_board.entity.User;
import com.boardbuilderslog.bulletin_board.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/check-username")
    @ResponseBody
    public Boolean checkUsername(
            @RequestParam @Size(min = 3, max = 20, message = "{username.size}")
            @Pattern(regexp = "^[a-zA-Z0-9_-]+$", message = "{username.pattern}")
            String username) {

        userService.checkUsername(username);
        return true;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/validatePassword")
    @ResponseBody
    public Map<String, Object> validatePassword(@Valid @RequestBody PasswordValidationRequest request,
                                                BindingResult result) throws MethodArgumentNotValidException {
        if (result.hasErrors()) {
            log.info("errors={}", result);
            throw new MethodArgumentNotValidException(null, result);
        }
        Map<String, Object> response = new HashMap<>();
        response.put("valid", true);
        return response;
    }

    @PostMapping("/registration")
    public String registrate(@Valid UserRegistrationRequest request,
                             BindingResult result) throws MethodArgumentNotValidException {
        if(result.hasErrors()){
            log.info("errors={}", result);
            throw new MethodArgumentNotValidException(null, result);
        }
        userService.registrate(request);
        return "index";
    }
    @PostMapping("/login")
    public String login(@Valid LoginRequest request, BindingResult result, HttpServletRequest httpRequest) throws MethodArgumentNotValidException {
        if(result.hasErrors()){
            return "login";
        }

        User loginUser= userService.login(request);

        if (loginUser == null){
            result.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            throw new MethodArgumentNotValidException(null, result);
        }

        // 세션이 있으면 기존 세션 반환, 없으면 신규 세션 반환
        // request.getSession(false) => 없으면 신규 세션을 생성하지 않고 null 반환한다.
        HttpSession session = httpRequest.getSession();
        session.setAttribute("loginUser", loginUser);

        return "redirect:/";
    }
}

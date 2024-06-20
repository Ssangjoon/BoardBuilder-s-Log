package com.boardbuilderslog.bulletin_board.controller;

import com.boardbuilderslog.bulletin_board.dto.PostCreate;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class MockTestController {

    @PostMapping
    public Map<String, String> get(@RequestBody @Valid PostCreate request, BindingResult result) throws MethodArgumentNotValidException {
        if(result.hasErrors()){
            throw new MethodArgumentNotValidException(null, result);
        }
        return Map.of();
    }
}

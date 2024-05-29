package com.boardbuilderslog.bulletin_board.controller;

import com.boardbuilderslog.bulletin_board.dto.PostCreateReqeust;
import com.boardbuilderslog.bulletin_board.dto.PostSelectOneResponse;
import com.boardbuilderslog.bulletin_board.service.PostService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    @GetMapping
    public String posts(Model model){
        List<PostSelectOneResponse> postResponses = postService.selectList();
        model.addAttribute("posts", postResponses);
        return "posts";
    }
    @GetMapping("/addPost")
    public String addPost(){
        log.info("PostController addPost");
        return "addPost";
    }
    @GetMapping("/{id}")
    public String post(@PathVariable Long id, Model model){
        log.info("PostController post");
        PostSelectOneResponse res = postService.selectOne(id);

        if (res != null) {
            model.addAttribute("post", res);
            return "post";
        } else {
            return "posts";
        }
    }
    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    @ResponseBody
    public long addItem(@Valid PostCreateReqeust dto, BindingResult result) throws MethodArgumentNotValidException, IOException {
        if (result.hasErrors()) {
            log.info("errors={}", result);
            throw new MethodArgumentNotValidException(null, result);
        }

        return postService.insertPost(dto);
    }
}

package com.boardbuilderslog.bulletin_board.controller;

import com.boardbuilderslog.bulletin_board.dto.PostCreateReqeust;
import com.boardbuilderslog.bulletin_board.dto.PostSelectOneResponse;
import com.boardbuilderslog.bulletin_board.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;

import java.io.IOException;

@Slf4j
@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    @GetMapping
    public String posts(Model model, @PageableDefault(size = 2, sort = "title") Pageable pageable){
        log.info(String.valueOf(pageable.getOffset()));
        Page<PostSelectOneResponse> postResponses = postService.selectList(pageable);
        model.addAttribute("posts", postResponses);
        return "post/posts";
    }
    @GetMapping("/{id}")
    public String post(@PathVariable Long id, Model model){
        log.info("PostController post");
        PostSelectOneResponse res = postService.selectOne(id);

        if (res != null) {
            model.addAttribute("post", res);
            return "post/post";
        } else {
            return "post/posts";
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

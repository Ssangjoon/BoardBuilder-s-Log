package com.boardbuilderslog.bulletin_board.service;

import com.boardbuilderslog.bulletin_board.dto.PostCreateReqeust;
import com.boardbuilderslog.bulletin_board.dto.PostSelectOneResponse;
import com.boardbuilderslog.bulletin_board.entity.Post;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface PostService {
    long insertPost(PostCreateReqeust dto) throws IOException;
    PostSelectOneResponse selectOne(Long id);
    List<PostSelectOneResponse> selectList();
}

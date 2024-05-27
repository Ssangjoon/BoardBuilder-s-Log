package com.boardbuilderslog.bulletin_board.service.impl;

import com.boardbuilderslog.bulletin_board.dto.PostCreateReqeust;
import com.boardbuilderslog.bulletin_board.dto.PostSelectOneResponse;
import com.boardbuilderslog.bulletin_board.entity.Post;
import com.boardbuilderslog.bulletin_board.repsoitory.PostRepository;
import com.boardbuilderslog.bulletin_board.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository repository;
    @Override
    public long insertPost(PostCreateReqeust dto) {
        return repository.save(dto.toEntity()).getId();
    }

    @Override
    public PostSelectOneResponse selectOne(Long id) {
        return repository.findByIdAndIsPublicTrue(id)
                .map(post -> PostSelectOneResponse.builder()
                        .title(post.getTitle())
                        .content(post.getContent())
                        .startDate(post.getStartDate())
                        .endDate(post.getEndDate())
                        .build())
                .orElse(null);
    }

    @Override
    public List<PostSelectOneResponse> selectList() {
        return repository.findAllByIsPublicTrue();
    }
}

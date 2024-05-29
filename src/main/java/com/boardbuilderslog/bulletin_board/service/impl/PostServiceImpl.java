package com.boardbuilderslog.bulletin_board.service.impl;

import com.boardbuilderslog.bulletin_board.dto.PostCreateReqeust;
import com.boardbuilderslog.bulletin_board.dto.PostSelectOneResponse;
import com.boardbuilderslog.bulletin_board.entity.Post;
import com.boardbuilderslog.bulletin_board.repsoitory.PostRepository;
import com.boardbuilderslog.bulletin_board.service.PostService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    @Value("${file.dir}")
    private String fileDir;
    private final PostRepository repository;
    @Transactional
    @Override
    public long insertPost(PostCreateReqeust dto) throws IOException {
        MultipartFile thumbnail = dto.getThumbnail();
        if(!thumbnail.isEmpty()){
            File directory = new File(fileDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            String fullPath = fileDir + "/" +thumbnail.getOriginalFilename();
            log.info("파일 저장 fullPath={}", fullPath);
            thumbnail.transferTo(new File(fullPath));
        }
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

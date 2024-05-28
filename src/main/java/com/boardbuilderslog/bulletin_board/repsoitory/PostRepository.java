package com.boardbuilderslog.bulletin_board.repsoitory;

import com.boardbuilderslog.bulletin_board.dto.PostSelectOneResponse;
import com.boardbuilderslog.bulletin_board.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    Optional<Post> findByIdAndIsPublicTrue(Long id);
    List<PostSelectOneResponse> findAllByIsPublicTrue();
}
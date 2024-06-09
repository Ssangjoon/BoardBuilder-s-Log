package com.boardbuilderslog.bulletin_board;

import com.boardbuilderslog.bulletin_board.entity.Address;
import com.boardbuilderslog.bulletin_board.entity.Post;
import com.boardbuilderslog.bulletin_board.entity.User;
import com.boardbuilderslog.bulletin_board.repsoitory.PostRepository;
import com.boardbuilderslog.bulletin_board.repsoitory.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class DataInitializer {
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        postRepository.save(Post.builder().title("제목1").content("내용").endDate(LocalDate.now()).startDate(LocalDate.now()).isPublic(true).build());
        postRepository.save(Post.builder().title("제목2").content("내용").endDate(LocalDate.now()).startDate(LocalDate.now()).isPublic(true).build());
        postRepository.save(Post.builder().title("제목3").content("내용").endDate(LocalDate.now()).startDate(LocalDate.now()).isPublic(true).build());
        postRepository.save(Post.builder().title("제목4").content("내용").endDate(LocalDate.now()).startDate(LocalDate.now()).isPublic(true).build());
        postRepository.save(Post.builder().title("제목5").content("내용").endDate(LocalDate.now()).startDate(LocalDate.now()).isPublic(true).build());
        Address addressEntity = Address.builder()
                .address("123 Main St")
                .roadAddress("456 Elm St")
                .zip("12345")
                .detailAddress("Apt 789")
                .build();
        User userEntity = User.builder()
                .username("ssang")
                .password("123")
                .fullName("sangjoon")
                .marketingConsent(true)
                .termsAccepted(true)
                .privacyPolicyAccepted(true)
                .address(addressEntity)
                .build();
        userRepository.save(userEntity);
    }
}

package com.boardbuilderslog.bulletin_board.service.impl;

import com.boardbuilderslog.bulletin_board.dto.LoginRequest;
import com.boardbuilderslog.bulletin_board.dto.UserRegistrationRequest;
import com.boardbuilderslog.bulletin_board.entity.Address;
import com.boardbuilderslog.bulletin_board.entity.Post;
import com.boardbuilderslog.bulletin_board.entity.User;
import com.boardbuilderslog.bulletin_board.repsoitory.UserRepository;
import com.boardbuilderslog.bulletin_board.service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Override
    public void checkUsername(String username) {
        if (repository.existsByUsername(username)) {
            throw new IllegalArgumentException("사용자 이름이 이미 사용 중입니다.");
        }
    }

    @Override
    public void registrate(UserRegistrationRequest request) {
        repository.save(request.toEntity());
    }

    @Override
    public User login(LoginRequest request) {
        return repository.findByUsername(request.getUsername()).orElse(null);
    }
}

package com.boardbuilderslog.bulletin_board.service.impl;

import com.boardbuilderslog.bulletin_board.repsoitory.UserRepository;
import com.boardbuilderslog.bulletin_board.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
}

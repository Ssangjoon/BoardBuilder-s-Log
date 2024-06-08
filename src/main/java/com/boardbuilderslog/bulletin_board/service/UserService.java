package com.boardbuilderslog.bulletin_board.service;

import com.boardbuilderslog.bulletin_board.dto.LoginRequest;
import com.boardbuilderslog.bulletin_board.dto.UserRegistrationRequest;
import com.boardbuilderslog.bulletin_board.entity.User;

public interface UserService {
    public void checkUsername(String username);
    public void registrate(UserRegistrationRequest request);
    public User login(LoginRequest request);
}

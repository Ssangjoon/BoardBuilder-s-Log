package com.boardbuilderslog.bulletin_board.service;

import com.boardbuilderslog.bulletin_board.dto.UserRegistrationRequest;

public interface UserService {
    public void checkUsername(String username);
    public void registrate(UserRegistrationRequest request);
}

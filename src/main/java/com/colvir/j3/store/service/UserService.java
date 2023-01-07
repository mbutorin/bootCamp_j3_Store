package com.colvir.j3.store.service;

import com.colvir.j3.store.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto save(UserDto userDto);
    UserDto update(UserDto userDto);
    void deleteByLogin(String login);
    UserDto findByLogin(String login);
}

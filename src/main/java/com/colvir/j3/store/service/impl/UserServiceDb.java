package com.colvir.j3.store.service.impl;

import com.colvir.j3.store.domain.UserEntity;
import com.colvir.j3.store.dto.UserDto;
import com.colvir.j3.store.exception.RecordBadData;
import com.colvir.j3.store.exception.UserNotFoundException;
import com.colvir.j3.store.repository.UserRepository;
import com.colvir.j3.store.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceDb implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserDto save(final UserDto userDto) {
        try {
        return new UserDto(
                userRepository.save(
                        new UserEntity(userDto)
                )
        );
        } catch (Exception e) {
            throw new RecordBadData("Can't save user: " + e.getMessage());
        }
    }

    @Override
    //@Transactional
    //@Lock(LockModeType.PESSIMISTIC_WRITE)
    public UserDto update(final UserDto userDto) {
        final UserEntity current = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new UserNotFoundException("Can't find user by id " + userDto.getId()));
        current.setUsername(userDto.getLogin());
        current.setNickname(userDto.getNickname());
        current.setUserpwd(userDto.getPassword());
        try {
            return new UserDto(userRepository.save(current));
        } catch (Exception e) {
            throw new RecordBadData("Can't save user: " + e.getMessage());
        }
    }

    @Override
    public void deleteByLogin(final String login) {
        userRepository.deleteById(findByLogin(login).getId());
    }

    @Override
    public UserDto findByLogin(final String login) {
        return userRepository.findByUsername(login)
                .map(UserDto::new)
                .orElseThrow(() -> new UserNotFoundException("Can't find user by login: " + login));
    }

    @Override
    public UserDto findById(Long id) {
        return userRepository.findById(id)
                .map(UserDto::new)
                .orElseThrow(() -> new UserNotFoundException("Can't find user by ID: " + id));
    }

}

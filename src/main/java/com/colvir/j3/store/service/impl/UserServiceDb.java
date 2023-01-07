package com.colvir.j3.store.service.impl;

import com.colvir.j3.store.domain.UserEntity;
import com.colvir.j3.store.dto.UserDto;
import com.colvir.j3.store.exception.UserBadData;
import com.colvir.j3.store.exception.UserNotFoundException;
import com.colvir.j3.store.repository.UserRepository;
import com.colvir.j3.store.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

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
            throw new UserBadData("Can't save user: " + e.getMessage());
        }
    }

    @Override
    //@Transactional
    //@Lock(LockModeType.PESSIMISTIC_WRITE)
    public UserDto update(final UserDto userDto) {
        final UserEntity current = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new UserNotFoundException("Can't find user by id " + userDto.getId()));
        current.setLogin(userDto.getLogin());
        current.setName(userDto.getName());
        current.setRole(userDto.getRole());
        try {
            return new UserDto(userRepository.save(current));
        } catch (Exception e) {
            throw new UserBadData("Can't save user: " + e.getMessage());
        }
    }

    @Override
    public void deleteByLogin(final String login) {
        userRepository.deleteById(findByLogin(login).getId());
    }

    @Override
    public UserDto findByLogin(final String login) {
        return userRepository.findByLogin(login)
                .map(UserDto::new)
                .orElseThrow(() -> new UserNotFoundException("Can't find user by login: " + login));
    }

}

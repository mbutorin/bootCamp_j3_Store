package com.colvir.j3.store.controller;

import com.colvir.j3.store.dto.UserDto;
import com.colvir.j3.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

//    @Autowired
//    public UserController(
//            @Qualifier("userServiceInMemory") final UserService userService
//    ) {
//        this.userService = userService;
//    }

    @Autowired
    public UserController(
            @Qualifier("userServiceDb") final UserService userService
    ) {
        this.userService = userService;
    }

    private final UserService userService;

    @ResponseStatus(code = HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public UserDto save(@Valid @RequestBody final UserDto userDto) {
        return userService.save(userDto);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public UserDto update(@Valid @RequestBody final UserDto userDto) {
        return userService.update(userDto);
    }

    @RequestMapping(method = RequestMethod.GET)
    public UserDto findByLogin(@RequestParam(name = "login") final String login) {
        return userService.findByLogin(login);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteByLogin(@RequestParam(name = "login") final String login) {
        userService.deleteByLogin(login);
    }
}

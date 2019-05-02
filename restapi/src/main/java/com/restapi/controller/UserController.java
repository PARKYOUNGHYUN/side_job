package com.restapi.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.restapi.entity.User;
import com.restapi.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * ユーザ操作のコントローラ
 */
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 全体ユーザ検索
     *
     * @return ユーザ
     */
    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    /**
     * ユーザ検索
     *
     * @param id ユーザID
     * @return ユーザ
     */
    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        Optional<User> user = userService.getUser(id); 
        if(user.isPresent())
            return new ResponseEntity<User>(user.get(), HttpStatus.OK);
        return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }

    /**
     * ユーザ登録
     *
     * @param userBody リクエストボディ
     * @return 登録後のユーザ
     */
    @PutMapping
    public ResponseEntity<User> createUser(@RequestBody @Validated User user) {
        User createdBoard = userService.createUser(user);
        if (createdBoard != null)
            return new ResponseEntity<User>(createdBoard, HttpStatus.CONFLICT);
        return new ResponseEntity<User>(HttpStatus.CREATED); 
    }

    /**
     * ユーザ更新
     *
     * @param userBody リクエストボディ
     * @return 更新後のユーザ
     */
    @PatchMapping("{id}")
    public ResponseEntity<User> patchUser(@PathVariable("id") Long id, @RequestBody @Valid User user) {
        User updateUser = userService.patchUser(id, user); 
        if(updateUser != null)
            return new ResponseEntity<User>(updateUser, HttpStatus.OK);
        return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }
}
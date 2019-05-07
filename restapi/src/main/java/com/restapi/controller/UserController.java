package com.restapi.controller;

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
     * ユーザ検索
     *
     * @param id ユーザID
     * @return ユーザ
     */
    @GetMapping
    public ResponseEntity<User> getUser(@RequestParam(required = false) String mail, @RequestParam(required = false) String password) {
        User user = userService.getUser(mail, password);
        if(user != null)
            return new ResponseEntity<User>(user, HttpStatus.OK);
        return new ResponseEntity<User>(HttpStatus.OK);
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
            return new ResponseEntity<User>(createdBoard, HttpStatus.CREATED);
        return new ResponseEntity<User>(HttpStatus.UNAUTHORIZED); 
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
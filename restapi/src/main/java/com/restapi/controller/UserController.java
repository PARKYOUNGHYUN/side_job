package com.restapi.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.restapi.entity.User;
import com.restapi.repository.UserRepository;
import com.restapi.service.UserService;

import lombok.NonNull;
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
@RequestMapping(path = "/api")
public class UserController {

    @Autowired
	private UserService userService;

    @NonNull
    private final UserRepository userRepository;

    /**
     * 全体ユーザ検索
     *
     * @param id 検索したいユーザID
     * @return ユーザ
     */
    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    /**
     * ユーザ検索
     *
     * @param id 検索したいユーザID
     * @return ユーザ
     */
    @GetMapping("/user/{id}")
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
     * @return 更新後のユーザ
     */
    @PutMapping("/user")
    public ResponseEntity<Void> createUser(@RequestBody @Validated User user) {
        boolean result = userService.createUser(user);
        if (!result)
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        return new ResponseEntity<Void>(HttpStatus.CREATED); 
    }

    /**
     * ユーザ修正
     *
     * @param userBody リクエストボディ
     * @return 更新後のユーザ
     */
    @PatchMapping("/user/{id}")
    public ResponseEntity<User> patchUser(@PathVariable("id") Long id, @RequestBody @Valid User user) {
        User updateUser = userService.patchUser(id, user); 
		if(updateUser != null)
			return new ResponseEntity<User>(updateUser, HttpStatus.OK);
        return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }

    /**
     * ユーザ削除
     *
     * @param id 削除したいユーザID
     */
    // @DeleteMapping("/users/{id}")
    // public ResponseEntity<?> deleteById(@PathVariable("id") String id) {
    //     User deleteUser = userRepository.findById(id).orElseThrow(() -> new NotFoundException(id + " is not found."));

    //     userRepository.delete(deleteUser);
    //     return ResponseEntity.ok().build();
    // }
}

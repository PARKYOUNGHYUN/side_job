package com.restapi.service;

import java.util.List;
import java.util.Optional;

import com.restapi.entity.User;
import com.restapi.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired(required=false)
    private PasswordEncoder passwordEncoder;

    public User getUser(String reqMail, String reqPassword) {
        List<User> checkedUser = userRepository.findByMail(reqMail);
        if(checkedUser.size() <= 0)
            return null;
        if(reqPassword != null) {
            String password = checkedUser.get(0).getPassword();
            passwordEncoder = new BCryptPasswordEncoder();
            if (! passwordEncoder.matches(reqPassword, password))
                return null;
        }
        return checkedUser.get(0);
    }

    public User createUser(User user) {
        try {
            passwordEncoder = new BCryptPasswordEncoder();
            String encryPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encryPassword);
            return userRepository.save(user);
        } catch (Exception e) {
            return null;
        }
    }
    
    // public User updateUser(long id, User user){
    //     final Optional<User> fetchedUser = userRepository.findById(id);
    //     if(fetchedUser.isPresent()){
    //         user.setId(id);
    //         return userRepository.save(user);
    //     }
    //     else{
    //         return null;
    //     }
    // }
    
    public User patchUser(long id, User user){
        final Optional<User> fetchedUser = userRepository.findById(id);
        if(fetchedUser.isPresent()){
            if(user.getNickname() != null) fetchedUser.get().setNickname(user.getNickname());
            if(user.getMail() != null) fetchedUser.get().setMail(user.getMail());
            if(user.getSex() > 0) fetchedUser.get().setSex(user.getSex());
            if(user.getBirth() != null) fetchedUser.get().setBirth(user.getBirth());
            if(user.getRegion() != null) fetchedUser.get().setRegion(user.getRegion());
            if(user.getPr() != null) fetchedUser.get().setPr(user.getPr());
            if(user.isDelFlg()) fetchedUser.get().setDelFlg(user.isDelFlg());
            
            return userRepository.save(fetchedUser.get());
        }
        else return null;
    }
}
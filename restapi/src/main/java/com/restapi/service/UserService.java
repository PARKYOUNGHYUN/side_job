package com.restapi.service;

import java.util.List;
import java.util.Optional;

import com.restapi.entity.User;
import com.restapi.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUser(Long id){
        return userRepository.findById(id);
    }

    // public List<User> getUserByMail(String mail) {
    //     return userRepository.findByMailEquals(mail);
    // }
    // public List<User> getUserByNickname(String nickname){
    //     return userRepository.findByNicknameEquals(nickname);
    // }

    public User createUser(User user) {
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            return null;
        }
    }
    
    // public User updateUser(Long id, User user){
    //     final Optional<User> fetchedUser = userRepository.findById(id);
    //     if(fetchedUser.isPresent()){
    //         user.setId(id);
    //         return userRepository.save(user);
    //     }
    //     else{
    //         return null;
    //     }
    // }
    
    public User patchUser(Long id, User user){
        final Optional<User> fetchedUser = userRepository.findById(id);
        if(fetchedUser.isPresent()){
            if(user.getNickname() != null) fetchedUser.get().setNickname(user.getNickname());
            if(user.getMail() != null) fetchedUser.get().setMail(user.getMail());
            if(user.getSex() > 0 && user.getSex() != fetchedUser.get().getSex())
                fetchedUser.get().setSex(user.getSex());
            if(user.getBirth() != null) fetchedUser.get().setBirth(user.getBirth());
            if(user.getRegion() != null) fetchedUser.get().setRegion(user.getRegion());
            if(user.getPr() != null) fetchedUser.get().setPr(user.getPr());
            if(user.isDelFlg()) fetchedUser.get().setDelFlg(user.isDelFlg());
            
            return userRepository.save(fetchedUser.get());
        }
        else return null;
    }
}
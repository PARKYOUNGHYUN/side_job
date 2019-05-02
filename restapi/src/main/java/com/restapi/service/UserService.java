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

	public boolean createUser(User user) {
		try {
			userRepository.save(user);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public List<User> getUsers() {
		return userRepository.findAll();
	}

	public Optional<User> getUser(Long id){
		return userRepository.findById(id);
	}

	// public User updateUser(Long id, User user){
	// 	final Optional<User> fetchedUser = userRepository.findById(id);
	// 	if(fetchedUser.isPresent()){
	// 		user.setId(id);
	// 		return userRepository.save(user);
	// 	}
	// 	else{
	// 		return null;
	// 	}
	// }
	
	public User patchUser(Long id, User user){
		final Optional<User> fetchedUser = userRepository.findById(id);
		if(fetchedUser.isPresent()){
			System.out.println("getSex = " + user.getSex());
			System.out.println("isDelFlg = " + user.isDelFlg());
            if(user.getNickname() != null) fetchedUser.get().setNickname(user.getNickname());
            if(user.getMail() != null) fetchedUser.get().setMail(user.getMail());
            if(user.getSex() > 0) fetchedUser.get().setSex(user.getSex());
            if(user.getBirth() != null) fetchedUser.get().setBirth(user.getBirth());
            if(user.getRegion() != null) fetchedUser.get().setRegion(user.getRegion());
            if(user.getPr() != null) fetchedUser.get().setPr(user.getPr());
			if(user.isDelFlg()) fetchedUser.get().setDelFlg(user.isDelFlg());
			
			return userRepository.save(fetchedUser.get());
		}
		else{
			return null;
		}
	}	

	
	// public boolean deleteUser(Long id){
	// 	final Optional<User> fetchedUser = userRepository.findById(id);
	// 	if(fetchedUser.isPresent()){
	// 		userRepository.deleteById(id);
	// 		return true;
	// 	}
	// 	else{
	// 		return false;
	// 	}
	// }
}
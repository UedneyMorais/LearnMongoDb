package com.learnmongodb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learnmongodb.UserDTO;
import com.learnmongodb.domain.User;
import com.learnmongodb.repository.UserRepository;
import com.learnmongodb.service.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll(){
		return userRepository.findAll();
	}

	
	public User findById(String id) {
		Optional<User> obj = userRepository.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public User insertUser(User user) {
		return userRepository.insert(user);
	}
	
	public Object deleteUserById(String id) {
		Optional<User> user = userRepository.findById(id);

		userRepository.deleteById(id);
		return user.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	
	public User updateUserById(User obj) {
		
		System.out.println(obj.getName());
		
		
		Optional<User> userObj = userRepository.findById(obj.getId());
		User user = userObj.orElseThrow(()-> new ObjectNotFoundException("Usuário não encontrado"));
		updateData(user, obj);
		userRepository.save(user);
		return user;
	}
	
	private void updateData(User user, User obj) {
		user.setName(obj.getName());
		user.setEmail(obj.getEmail());
		user.setPassword(obj.getPassword());
	}
	
	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail(), objDto.getPassword());
	}
	

}

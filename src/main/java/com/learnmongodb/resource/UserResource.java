package com.learnmongodb.resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learnmongodb.UserDTO;
import com.learnmongodb.domain.User;
import com.learnmongodb.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@Autowired
	public UserService service;
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll(){
				
		List<User> listUser = service.findAll();
		List<UserDTO> listUserDTO = listUser.stream().map((u) -> new UserDTO(u)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listUserDTO);	
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id){
				
		User user = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(user));
	}
	

}

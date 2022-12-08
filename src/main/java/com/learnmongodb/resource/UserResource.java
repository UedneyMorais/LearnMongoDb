package com.learnmongodb.resource;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	
	@PostMapping
	public ResponseEntity<Void> insertUser(@RequestBody UserDTO objDTO){
		
		User user = service.fromDTO(objDTO);
		user = service.insertUser(user);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletUserById(@PathVariable String id) {
		service.deleteUserById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> updateUserById(@PathVariable String id ,@RequestBody UserDTO objDTO){
		
		User user = service.fromDTO(objDTO);
		user.setId(id);
		service.updateUserById(user);

		return ResponseEntity.noContent().build();
	}

}

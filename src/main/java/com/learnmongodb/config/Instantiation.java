package com.learnmongodb.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.learnmongodb.domain.User;
import com.learnmongodb.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		User uedney = new User(null, "Uedney", "uedneymorais@gmail.com", "202cb962ac59075b964b07152d234b70");
		User lara = new User(null, "Lara", "lara@gmail.com","202cb962ac59075b964b07152d234b70");
		User ericka = new User(null, "Ericka", "ericka@gmail.com","202cb962ac59075b964b07152d234b70");
		
		userRepository.deleteAll();
		userRepository.saveAll(Arrays.asList(uedney,lara,ericka));
		
		
	}

}

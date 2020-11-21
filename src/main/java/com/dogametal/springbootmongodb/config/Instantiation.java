package com.dogametal.springbootmongodb.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.dogametal.springbootmongodb.domain.User;
import com.dogametal.springbootmongodb.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {	
		
		userRepository.deleteAll();
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		User douglas = new User(null, "Douglas Silva", "douglas.it.soares@gmail.com");
		User daniel = new User(null, "Daniel Soares", "daniel.bebe.soares@gmail.com");
		userRepository.saveAll(Arrays.asList(maria, alex, bob, douglas, daniel));
	}
	
	

}

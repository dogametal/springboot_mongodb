package com.dogametal.springbootmongodb.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.dogametal.springbootmongodb.domain.Post;
import com.dogametal.springbootmongodb.domain.User;
import com.dogametal.springbootmongodb.dto.AuthorDTO;
import com.dogametal.springbootmongodb.repository.PostRepository;
import com.dogametal.springbootmongodb.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {	
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		User douglas = new User(null, "Douglas Silva", "douglas.it.soares@gmail.com");
		User daniel = new User(null, "Daniel Soares", "daniel.bebe.soares@gmail.com");
		userRepository.saveAll(Arrays.asList(maria, alex, bob, douglas, daniel));
		
		postRepository.deleteAll();
		Post post1 = new Post(null,sdf.parse("26/11/2020"), "Partiu nascimento", "Seja bem vindo, filho !", new AuthorDTO(douglas));
		Post post2 = new Post(null,sdf.parse("27/11/2020"), "Nasceu  !!!", "Papai ama Daniel ", new AuthorDTO(douglas));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		douglas.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.saveAll(Arrays.asList(douglas));
		
	}
	
	

}

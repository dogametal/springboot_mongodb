package com.dogametal.springbootmongodb.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dogametal.springbootmongodb.domain.User;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@RequestMapping(method = RequestMethod.GET) // Another way to use this request @GetMapping 	
	public ResponseEntity <List<User>> findAll(){
		User Douglas = new User("1","Douglas Silva", "douglas.it.soares@gmail.com");
		User Daniel = new User("2","Daniel Sousa Soares", "daniel.bebe.soares@gmail.com");
 
		List<User> list = new ArrayList<>();
		list.addAll(Arrays.asList(Douglas, Daniel));
		
		return ResponseEntity.ok().body(list); //Another to do the same - return ResponseEntity.ok(list);

	}
}

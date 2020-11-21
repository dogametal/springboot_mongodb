package com.dogametal.springbootmongodb.resources;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dogametal.springbootmongodb.domain.User;
import com.dogametal.springbootmongodb.dto.UserDTO;
import com.dogametal.springbootmongodb.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService service;
	
	
	@RequestMapping(method = RequestMethod.GET) // Another way to use this request @GetMapping 	
	public ResponseEntity <List<UserDTO>> findAll(){
		List<User> list = service.findAll();
		//Method to tranform each item from list to ListDTO using lambda with collectors
		List<UserDTO> listDto = list.stream().map(item -> new UserDTO(item)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto); //Another to do the same - return ResponseEntity.ok(list);

	}
}

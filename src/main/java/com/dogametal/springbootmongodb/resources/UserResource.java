package com.dogametal.springbootmongodb.resources;

import java.net.URI;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
		Comparator<User> comp = (p1, p2)  -> p1.getName().toUpperCase().compareTo(p2.getName().toUpperCase());
		list.sort(comp);
		
		//Method to tranform each item from list to ListDTO using lambda with collectors
		List<UserDTO> listDto = list.stream().map(item -> new UserDTO(item)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto); //Another to do the same - return ResponseEntity.ok(list);

	}

	@RequestMapping(value ="/{id}", method = RequestMethod.GET) // Another way to use this request @GetMapping
	public ResponseEntity <UserDTO> findbyId(@PathVariable String id){		
		User obj = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(obj)); //Create new object DTO for this searching 

	}
	
	@RequestMapping(method = RequestMethod.POST) // Another way to use this request @PostMapping
	public ResponseEntity <Void> insert(@RequestBody UserDTO objDTO){		
		//Convert userDTO to User
		User obj = service.fromDTO(objDTO);
		obj = service.insert(obj);
		//Get address new object add
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		//Create status 201 right response for this method		 
		return ResponseEntity.created(uri).build();

	}	

	@RequestMapping(value ="/{id}", method = RequestMethod.DELETE) 
	public ResponseEntity <Void> delete(@PathVariable String id){		
		service.delete(id);
		return ResponseEntity.noContent().build(); 
	}
	
}

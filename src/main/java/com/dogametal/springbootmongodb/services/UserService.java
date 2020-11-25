package com.dogametal.springbootmongodb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogametal.springbootmongodb.domain.User;
import com.dogametal.springbootmongodb.dto.UserDTO;
import com.dogametal.springbootmongodb.repository.UserRepository;
import com.dogametal.springbootmongodb.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired //Annotation capable to return with all information from User
	private UserRepository repo;
	
	public List<User> findAll(){
		return repo.findAll();
		
	}
	
	public User findById(String id) {		
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado !"));
	}
	
	public User insert (User obj) {
		return repo.insert(obj);
	}
	
	public void delete (String id) {
		//Case id not exists throw exception
		findById(id);
		repo.deleteById(id);
	}
	
	public User fromDTO(UserDTO objDTO) {
		return new User(objDTO.getId(), objDTO.getName(), objDTO.getEmail());
		
	}
}

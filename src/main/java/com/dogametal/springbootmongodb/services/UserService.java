package com.dogametal.springbootmongodb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogametal.springbootmongodb.domain.User;
import com.dogametal.springbootmongodb.repository.UserRepository;

@Service
public class UserService {

	@Autowired //Annotation capable to return with all information from User
	private UserRepository repo;
	
	public List<User> findAll(){
		return repo.findAll();
		
	}
}

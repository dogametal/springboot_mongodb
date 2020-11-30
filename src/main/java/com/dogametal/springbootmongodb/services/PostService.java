package com.dogametal.springbootmongodb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogametal.springbootmongodb.domain.Post;
import com.dogametal.springbootmongodb.repository.PostRepository;
import com.dogametal.springbootmongodb.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired //Annotation capable to return with all information from User
	private PostRepository repo;
	

	public Post findById(String id) {		
		Optional<Post> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado !"));
	}
	
	public List<Post> findByTitle (String text){
		// method used by spring framework
		//return repo.findByTitleContainingIgnoreCase(text);
		return repo.searchTitle(text);
	}
}

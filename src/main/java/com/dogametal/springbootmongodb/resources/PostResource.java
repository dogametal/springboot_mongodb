package com.dogametal.springbootmongodb.resources;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dogametal.springbootmongodb.domain.Post;
import com.dogametal.springbootmongodb.resources.util.URL;
import com.dogametal.springbootmongodb.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

	@Autowired
	private PostService service;
	
	
	@RequestMapping(value ="/{id}", method = RequestMethod.GET) // Another way to use this request @GetMapping
	public ResponseEntity <Post> findbyId(@PathVariable String id){		
		Post obj = service.findById(id);
		return ResponseEntity.ok().body(obj);  
	}

	@RequestMapping(value ="/titlesearch", method = RequestMethod.GET) // Another way to use this request @GetMapping
	public ResponseEntity <List<Post>> findbyTitle(@RequestParam(value="text", defaultValue = "") String text ){		
		text = URL.decodeParam(text);
		List<Post> list = service.findByTitle(text);
		return ResponseEntity.ok().body(list);  
	}
	
	@RequestMapping(value ="/fullsearch", method = RequestMethod.GET) // Another way to use this request @GetMapping
	public ResponseEntity <List<Post>> fullSearch(
			@RequestParam(value="text", defaultValue = "") String text ,
			@RequestParam(value="minDate", defaultValue = "") String minDate ,
			@RequestParam(value="maxDate", defaultValue = "") String maxDate ){		
		text = URL.decodeParam(text);		
		Date min = URL.convertDate(minDate, new Date(0L)); //In case minDate problems send defaultValue System
		Date max = URL.convertDate(maxDate, new Date());
		List<Post> list = service.fullSearch(text, min, max);
		return ResponseEntity.ok().body(list);  
	}
}

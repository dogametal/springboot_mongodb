package com.dogametal.springbootmongodb.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.dogametal.springbootmongodb.domain.Post;

@Repository
//MongoRepository(Type first Entity and type of ID this case is String)
public interface PostRepository extends MongoRepository<Post, String> {

	
	//this way to get text as parameter and ignore word Upper or LowCase	
	@Query("{'title': {$regex: ?0, $options: 'i'}}")
	List<Post> searchTitle (String text);

	@Query ("{ $and: [ {date: {$gte: ?1} }, { date: { $lte: ?2} }, { $or: [ {'title': {$regex: ?0, $options: 'i'}}, {'body': {$regex: ?0, $options: 'i'}}, {'comments.text': {$regex: ?0, $options: 'i'}} ] } ] }")
	List<Post> fullSearch(String text, Date minDate, Date maxDate);
	
	List<Post> findByTitleContainingIgnoreCase (String text);
}

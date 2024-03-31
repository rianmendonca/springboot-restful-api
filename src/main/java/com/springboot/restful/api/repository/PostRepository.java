package com.springboot.restful.api.repository;

import com.springboot.restful.api.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

    List<Post> findByTitleContainingIgnoreCase(String text);

    @Query("{'title': { $regex:  ?0, $options:  'i'} }")
    List<Post> searchTitle(String text);

}

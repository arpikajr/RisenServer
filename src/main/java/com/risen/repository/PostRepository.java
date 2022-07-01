package com.risen.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.risen.model.Post;
import com.risen.model.SubRisen;
import com.risen.model.User;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
	
	List<Post> findAllBySubRisen(SubRisen subRisen);
	
	List<Post> findByUser(User user);

}

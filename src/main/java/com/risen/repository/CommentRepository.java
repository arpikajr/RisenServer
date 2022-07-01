package com.risen.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.risen.model.Comment;
import com.risen.model.Post;
import com.risen.model.User;

public interface CommentRepository extends JpaRepository<Comment, Long>{

	List<Comment> findByPost(Post post);
	List<Comment> findAllByUser(User user);
	
}

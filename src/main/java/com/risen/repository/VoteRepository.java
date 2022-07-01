package com.risen.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.risen.model.Post;
import com.risen.model.User;
import com.risen.model.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long>{

	 Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);
	
}

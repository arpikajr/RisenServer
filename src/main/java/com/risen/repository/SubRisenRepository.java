package com.risen.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.risen.model.SubRisen;

@Repository
public interface SubRisenRepository extends JpaRepository<SubRisen, Long>{

	Optional<SubRisen> findByName(String subRisenName);
	
}

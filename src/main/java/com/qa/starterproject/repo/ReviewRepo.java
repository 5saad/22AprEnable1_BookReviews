package com.qa.starterproject.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qa.starterproject.domain.Review;

@Repository
public interface ReviewRepo extends JpaRepository<Review, Long> {
	
	@Query(value = "SELECT * FROM REVIEW WHERE rating = ?1", nativeQuery = true)
	public List<Review> findByRating(int num);
	
}

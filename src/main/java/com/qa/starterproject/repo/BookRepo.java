package com.qa.starterproject.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qa.starterproject.domain.Book;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {
	
//	@Query(value = "SELECT * FROM BOOK WHERE AUTHOR LIKE '%?1%'", nativeQuery = true)
	public List<Book> findByTitleContains(String str);
	
	

	
}

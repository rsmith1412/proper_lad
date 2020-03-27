package com.robert.properLad.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.robert.properLad.models.Review;

@Repository
public interface ReviewRepo extends CrudRepository<Review, Long> {
	List<Review> findAllByProductId(Long id);
}

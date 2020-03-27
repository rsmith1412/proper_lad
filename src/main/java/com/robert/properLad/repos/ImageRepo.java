package com.robert.properLad.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.robert.properLad.models.Image;

public interface ImageRepo extends CrudRepository<Image, Long> {
	Image findImageById(Long id);
	List<Image> findAllByProductId(Long id);
}

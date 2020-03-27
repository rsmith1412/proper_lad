package com.robert.properLad.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.robert.properLad.models.Image;
import com.robert.properLad.repos.ImageRepo;

@Service
public class ImageService {
	private ImageRepo imgRepo;
	
	public ImageService(ImageRepo imgRepo) {
		this.imgRepo = imgRepo;
	}
	
	public Image createImg(Image img) {
		return imgRepo.save(img);
	}
	public List<Image> findImagesByProductId(Long id) {
		return imgRepo.findAllByProductId(id);
	}

}

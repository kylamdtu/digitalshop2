package com.dtucdio3.digitalshop2.service;

import com.dtucdio3.digitalshop2.entity.Image;
import com.dtucdio3.digitalshop2.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {
    ImageRepository imageRepository;

    @Autowired
    public void setImageRepository(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public void save(Image image) {
        imageRepository.save(image);
    }

    public List<Image> findAll() {
        return imageRepository.findAll();
    }
}

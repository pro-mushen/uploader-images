package com.example.delete.demo.services;

import com.example.delete.demo.dao.pojo.Base64Image;
import org.springframework.web.multipart.MultipartFile;

public interface WriterImagesService {
    void upload(MultipartFile image);
    void upload(MultipartFile[] images);

    void upload(Base64Image image);

    void upload(Base64Image[] images);

}

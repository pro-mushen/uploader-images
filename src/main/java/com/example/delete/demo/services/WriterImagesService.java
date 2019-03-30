package com.example.delete.demo.services;

import org.springframework.web.multipart.MultipartFile;

public interface WriterImagesService {
    void upload(MultipartFile image);

    void upload(MultipartFile[] images);

}

package com.example.delete.demo.dao;

import org.springframework.web.multipart.MultipartFile;

public interface WriterImagesDao {
    void upload(MultipartFile image);
}

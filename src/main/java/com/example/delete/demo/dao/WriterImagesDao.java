package com.example.delete.demo.dao;

import com.example.delete.demo.dao.pojo.Base64Image;
import org.springframework.web.multipart.MultipartFile;

public interface WriterImagesDao {
    void upload(MultipartFile image);

    void upload(Base64Image image);
}

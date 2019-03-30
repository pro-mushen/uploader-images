package com.example.delete.demo.dao;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


@Repository
public class WriterImagesDaoImp implements WriterImagesDao {
    @Value("${upload.path}")
    private String pathUpload;

    @Override
    public void upload(MultipartFile image) {
        File dirUpload = new File(pathUpload);

        if (!dirUpload.exists()) {
            dirUpload.mkdir();
        }

        try {
            image.transferTo(new File(dirUpload.getAbsolutePath() + File.separator + image.getOriginalFilename()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getPathUpload() {
        return pathUpload;
    }

    public void setPathUpload(String pathUpload) {
        this.pathUpload = pathUpload;
    }
}

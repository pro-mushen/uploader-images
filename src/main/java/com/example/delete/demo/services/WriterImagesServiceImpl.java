package com.example.delete.demo.services;

import com.example.delete.demo.dao.WriterImagesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;


@Service
public class WriterImagesServiceImpl implements WriterImagesService {

    @Autowired
    private WriterImagesDao writerImagesDao;

    @Override
    public void upload(MultipartFile image) {
        writerImagesDao.upload(image);
    }

    @Override
    public void upload(MultipartFile[] images) {
        Arrays.asList(images).parallelStream().forEach(image -> upload(image));
    }


    public WriterImagesDao getWriterImagesDao() {
        return writerImagesDao;
    }


    public void setWriterImagesDao(WriterImagesDao writerImagesDao) {
        this.writerImagesDao = writerImagesDao;
    }
}

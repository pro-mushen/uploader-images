package ru.mirzoyan.upload.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.mirzoyan.upload.dao.WriterImagesDao;
import ru.mirzoyan.upload.dao.pojo.Base64Image;

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

    @Override
    public void upload(Base64Image base64Image) {
        writerImagesDao.upload(base64Image);
    }

    @Override
    public void upload(Base64Image[] base64Images) {
        Arrays.asList(base64Images).parallelStream().forEach(base64Image -> upload(base64Image));
    }


    public WriterImagesDao getWriterImagesDao() {
        return writerImagesDao;
    }


    public void setWriterImagesDao(WriterImagesDao writerImagesDao) {
        this.writerImagesDao = writerImagesDao;
    }
}

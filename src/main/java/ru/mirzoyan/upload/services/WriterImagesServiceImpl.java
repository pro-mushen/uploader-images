package ru.mirzoyan.upload.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.mirzoyan.upload.dao.WriterImagesDao;
import ru.mirzoyan.upload.dao.pojo.Image;

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
        Arrays.asList(images).forEach(image -> upload(image));
    }

    @Override
    public void upload(Image image) {
        writerImagesDao.upload(image);
    }

    @Override
    public void upload(Image[] images) {
        Arrays.asList(images).parallelStream().forEach(base64Image -> upload(base64Image));
    }

    public WriterImagesDao getWriterImagesDao() {
        return writerImagesDao;
    }

    public void setWriterImagesDao(WriterImagesDao writerImagesDao) {
        this.writerImagesDao = writerImagesDao;
    }
}

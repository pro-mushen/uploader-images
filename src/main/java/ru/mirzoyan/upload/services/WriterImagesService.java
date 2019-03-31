package ru.mirzoyan.upload.services;

import org.springframework.web.multipart.MultipartFile;
import ru.mirzoyan.upload.dao.pojo.Base64Image;

public interface WriterImagesService {
    void upload(MultipartFile image);
    void upload(MultipartFile[] images);

    void upload(Base64Image image);

    void upload(Base64Image[] images);

}

package ru.mirzoyan.upload.services;

import org.springframework.web.multipart.MultipartFile;
import ru.mirzoyan.upload.dao.pojo.Image;

import java.util.List;

public interface WriterImagesService {
    void upload(MultipartFile image);

    void upload(MultipartFile[] images);

    void upload(Image image);

    void upload(List<Image> images);
}

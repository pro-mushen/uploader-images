package ru.mirzoyan.upload.dao;

import org.springframework.web.multipart.MultipartFile;
import ru.mirzoyan.upload.dao.pojo.Image;

public interface WriterImagesDao {
    void upload(MultipartFile image);

    void upload(Image image);
}

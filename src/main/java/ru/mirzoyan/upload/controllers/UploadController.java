package ru.mirzoyan.upload.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.mirzoyan.upload.dao.pojo.Image;
import ru.mirzoyan.upload.services.WriterImagesService;

@RestController
public class UploadController {

    WriterImagesService writerImagesService;

    @PostMapping(path = "/upload", consumes = "multipart/form-data")
    public void upload(
            @RequestParam("fileMulti") MultipartFile[] images) {
        writerImagesService.upload(images);
    }


/*    @PostMapping(path = "/upload", consumes = "application/x-www-form-urlencoded")
    public void upload(
            @RequestBody(required = false) String[] urls) {
        writerImagesService.upload(urls);
    }*/

    @PostMapping(path = "/upload", consumes = "application/json")
    public void upload(
            @RequestBody Image[] images) {
        writerImagesService.upload(images);
    }

    public WriterImagesService getWriterImagesService() {
        return writerImagesService;
    }

    @Autowired
    public void setWriterImagesService(WriterImagesService writerImagesService) {
        this.writerImagesService = writerImagesService;
    }
}

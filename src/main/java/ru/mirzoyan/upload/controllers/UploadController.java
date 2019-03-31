package ru.mirzoyan.upload.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.mirzoyan.upload.dao.pojo.Base64Image;
import ru.mirzoyan.upload.services.WriterImagesService;

@RestController
public class UploadController {

    WriterImagesService writerImagesService;

    @PostMapping(path = "/upload", consumes = "multipart/form-data")
    public void upload(
            @RequestParam("file") MultipartFile[] images) {
        writerImagesService.upload(images);
    }


    @PostMapping(path = "/upload", consumes = "application/json")
    public void upload(
            @RequestBody Base64Image[] base64Images) {
        writerImagesService.upload(base64Images);
    }

/*    @RequestMapping( value = "/upload")
    public String get(){
        return "asd";
    }*/


    public WriterImagesService getWriterImagesService() {
        return writerImagesService;
    }

    @Autowired
    public void setWriterImagesService(WriterImagesService writerImagesService) {
        this.writerImagesService = writerImagesService;
    }
}

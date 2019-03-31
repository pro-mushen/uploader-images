package com.example.delete.demo.controllers;

import com.example.delete.demo.services.WriterImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {

    WriterImagesService writerImagesService;

    @PostMapping(path = "/upload", consumes = "multipart/form-data")
    public void upload(
            @RequestParam("file") MultipartFile[] images) {
        writerImagesService.upload(images);
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

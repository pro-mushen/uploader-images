package com.example.delete.demo.controllers;

import com.example.delete.demo.services.WriterImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

@RestController
public class UploadController {

    WriterImagesService writerImagesService;

    @PostMapping(path = "/upload")
    public String upload(
            @RequestParam("file") MultipartFile[] image) {
        writerImagesService.upload(image);
        return Arrays.toString(image);
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

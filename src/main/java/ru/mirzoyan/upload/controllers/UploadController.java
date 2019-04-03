package ru.mirzoyan.upload.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.mirzoyan.upload.dao.pojo.Image;
import ru.mirzoyan.upload.services.WriterImagesService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
public class UploadController {

    WriterImagesService writerImagesService;

    @PostMapping(path = "/upload", consumes = {"application/json", "multipart/form-data"})
    public void upload(@RequestParam(value = "fileMulti", required = false) MultipartFile[] multipartFiles, HttpServletRequest httpServletRequest) {
        if (multipartFiles != null) {
            writerImagesService.upload(multipartFiles);
        } else {
            try {
                String json = IOUtils.toString(httpServletRequest.getReader());
                List<Image> images = new ObjectMapper().readValue(json, new TypeReference<List<Image>>() {});
                writerImagesService.upload(images);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public WriterImagesService getWriterImagesService() {
        return writerImagesService;
    }

    @Autowired
    public void setWriterImagesService(WriterImagesService writerImagesService) {
        this.writerImagesService = writerImagesService;
    }
}

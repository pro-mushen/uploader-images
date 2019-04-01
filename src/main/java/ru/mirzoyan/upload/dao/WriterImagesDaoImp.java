package ru.mirzoyan.upload.dao;


import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import ru.mirzoyan.upload.dao.pojo.Image;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


@Repository
public class WriterImagesDaoImp implements WriterImagesDao {
    @Value("${upload.path}")
    private String pathUpload;

    @Override
    public void upload(MultipartFile image) {
        File dirUpload = getDirUpload();
        try {
            image.transferTo(new File(dirUpload.getAbsolutePath() + File.separator + image.getOriginalFilename()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void upload(Image image) {
        if (image.getEncoded() != null) {
            uploadBase64(image);
        }
        if (image.getUrl() != null) {
            uploadUrl(image);
        }
    }

    private void uploadUrl(Image image) {
        try {
            URL url = new URL(image.getUrl());
            String nameImage = image.getName();
            if (nameImage == null) {
                nameImage = FilenameUtils.getName(url.getPath());
            }
            String fullPath = getDirUpload().getAbsolutePath() + File.separator + nameImage;
            try (InputStream in = url.openStream()) {
                Files.copy(in, Paths.get(fullPath), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void uploadBase64(Image image) {
        File dirUpload = getDirUpload();
        byte[] decodedBytes = DatatypeConverter.parseBase64Binary(fixEncoded(image.getEncoded()));
        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(dirUpload.getAbsoluteFile() + File.separator + image.getName()))) {
            outputStream.write(decodedBytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private String getNameImage(URL url) {
        return FilenameUtils.getName(url.getPath());
    }

    private File getDirUpload() {
        File dirUpload = new File(pathUpload);
        if (!dirUpload.exists()) {
            dirUpload.mkdir();
        }
        return dirUpload;
    }

    private String fixEncoded(String encoded) {
        String fixEncoded = "";
        if (encoded != null) {
            String[] splitEncoded = encoded.split(",");
            fixEncoded = splitEncoded[1];
        }
        return fixEncoded;
    }

    public String getPathUpload() {
        return pathUpload;
    }

    public void setPathUpload(String pathUpload) {
        this.pathUpload = pathUpload;
    }
}

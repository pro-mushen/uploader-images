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
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;


@Repository
public class WriterImagesDaoImp implements WriterImagesDao {
    @Value("${upload.path}")
    private String pathUpload;

    private Set<String> setImages = new HashSet<>();
    private static final Logger LOGGER = Logger.getLogger(WriterImagesDaoImp.class.getName());

    @Override
    public void upload(MultipartFile image) {
        try {
            image.transferTo(new File(getNewFullName(image.getOriginalFilename())));
        } catch (IOException e) {
            LOGGER.warning(e.getMessage());
        }
    }

    @Override
    public void upload(Image image) {
        if (image.getEncoded() != null) {
            uploadBase64(image);
        } else if (image.getUrl() != null) {
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
            try (InputStream in = url.openStream()) {
                Files.copy(in, Paths.get(getNewFullName(nameImage)), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                LOGGER.warning(e.getMessage());
            }
        } catch (MalformedURLException e) {
            LOGGER.warning(e.getMessage());
        }

    }

    private void uploadBase64(Image image) {
        byte[] decodedBytes = DatatypeConverter.parseBase64Binary(fixEncoded(image.getEncoded()));
        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(getNewFullName(image.getName())))) {
            outputStream.write(decodedBytes);
        } catch (IOException e) {
            LOGGER.warning(e.getMessage());
        }
    }


    private synchronized String getNewFullName(String name) {
        String newFullName = getDirUpload().getAbsolutePath() + File.separator + name;
        File file = new File(newFullName);
        int counter = 0;
        while (setImages.contains(newFullName) || file.exists()) {
            counter++;
            newFullName = getDirUpload().getAbsolutePath() + File.separator + "(" + counter + ")" + name;
            file = new File(newFullName);
        }
        setImages.add(newFullName);
        return newFullName;
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

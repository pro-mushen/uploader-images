package ru.mirzoyan.upload.dao;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import ru.mirzoyan.upload.dao.pojo.Base64Image;

import javax.xml.bind.DatatypeConverter;
import java.io.*;


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

    public void upload(Base64Image base64Image) {
        File dirUpload = getDirUpload();
        byte[] decodedBytes = DatatypeConverter.parseBase64Binary(fixEncoded(base64Image.getEncoded()));
        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(dirUpload.getAbsoluteFile() + File.separator + base64Image.getName()))) {
            outputStream.write(decodedBytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

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

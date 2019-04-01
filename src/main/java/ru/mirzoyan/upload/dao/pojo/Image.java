package ru.mirzoyan.upload.dao.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

@Component
public class Image {
    @JsonProperty("name")
    private String name;
    @JsonProperty("encoded")
    private String encoded;
    @JsonProperty("url")
    private String url;

    public Image() {
    }

    public Image(String name, String encoded, String url) {
        this.name = name;
        this.encoded = encoded;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEncoded() {
        return encoded;
    }

    public void setEncoded(String encoded) {
        this.encoded = encoded;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

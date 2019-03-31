package ru.mirzoyan.upload.dao.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

@Component
public class Base64Image {
    @JsonProperty("name")
    private String name;
    @JsonProperty("encoded")
    private String encoded;

    public Base64Image() {
    }

    public Base64Image(String name, String encoded) {
        this.name = name;
        this.encoded = encoded;
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
}

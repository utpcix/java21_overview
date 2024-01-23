package pe.edu.utp.features.http;

import pe.edu.utp.utils.UTPBinary;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public record Resource(byte[] data, String type) {

    // Builder of
    public static Resource of(byte... data){
        return new Resource(data,"application/octet-stream");
    }

    // Wrapper for builder from filename
    public static Resource of(String filename){
        return Resource.of(UTPBinary.readBinData(filename));
    }

    // Builder from base64 string
    public static Resource ofBase64(String base64, String type){
        return new Resource(Base64.getDecoder().decode(base64), type);
    }

    // Builder from String and type
    public static Resource ofText(String text, String type){
        return new Resource(text.getBytes(StandardCharsets.UTF_8),type);
    }

    // Wrapper from text
    public static Resource ofText(String text){
        return ofText(text, "text/plain");
    }

    // Wrapper from html
    public static Resource ofHTML(String html){
        return ofText(html, "text/html");
    }
}

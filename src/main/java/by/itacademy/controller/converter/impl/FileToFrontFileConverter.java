package by.itacademy.controller.converter.impl;

import by.itacademy.entities.file.File;
import by.itacademy.entities.front.FrontFile;
import by.itacademy.controller.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class FileToFrontFileConverter implements Converter<File, FrontFile> {
    @Override

    public FrontFile convert(File file) {
        byte[] bytes = file.getBytes();
        String base64 = Base64.getEncoder().encodeToString(bytes);
        return new FrontFile(file.getName(), base64);
    }
}

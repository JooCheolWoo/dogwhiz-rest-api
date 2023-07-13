package com.galaxypoby.dogwhiz.util;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.*;
import java.awt.*;
import java.io.*;

@Component
public class ImageResize {

    public void imageResize(MultipartFile multipartFile) throws IOException {
        File file = convertToFile(multipartFile);
        InputStream inputStream = new FileInputStream(file);
        Image img = new ImageIcon(file.toString()).getImage();

        int originWidth = img.getWidth(null);
        int originHeight = img.getHeight(null);

        

    }

    private File convertToFile(MultipartFile multipartFile) throws IOException {
        File file = new File(multipartFile.getOriginalFilename());
        file.createNewFile();
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(multipartFile.getBytes());
        fos.close();
        return file;
    }
}

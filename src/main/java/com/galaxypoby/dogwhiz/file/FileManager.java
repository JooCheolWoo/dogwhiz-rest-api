package com.galaxypoby.dogwhiz.file;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

@Component
public class FileManager {

    private final Path rootLocation = Paths.get("https://dev.api.hellodogwhiz.com//home/galaxypoby/storage/dogwhiz-dev/");

    public void uploadFile(MultipartFile file) {
        try(
                /* 맥일 경우
                FileOutputStream fos = new FileOutputStream(
                    "/tmp/" + file.getOriginalFilename() + new Date());
                */

                // 윈도우일 경우
                FileOutputStream fos = new FileOutputStream(
                        "rootLocation" + file.getOriginalFilename() + new Date());
                InputStream is = file.getInputStream();
        ){
            int readCount = 0;
            byte[] buffer = new byte[1024];
            int i = 0;
            while((readCount = is.read(buffer)) != -1){
                fos.write(buffer, 0, readCount);
                System.out.println(i++);
            }
        }catch(Exception ex){
            throw new RuntimeException("file Save Error");
        }
    }
}

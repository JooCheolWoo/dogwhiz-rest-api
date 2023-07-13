package com.galaxypoby.dogwhiz.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class FileManager {

    @Value("${path.rootURL}")
    private String rootURL;

    public Map<String, String> fileUpload(String folder, MultipartFile file) {
        String rootFolder = "/home";

        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf('.'));
        String newFilename = UUID.randomUUID().toString() + extension;

        File directory = new File(rootFolder + "/" + folder);
        if (!directory.exists()){
            directory.mkdirs();
        }

        String savePath = Paths.get(rootFolder, folder, newFilename).toString();
        String webURL = rootURL + "/" + folder + "/" + newFilename;

        File saveFile = new File(savePath);
        try {
            file.transferTo(saveFile);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, String> result = new HashMap<>();
        result.put("path", savePath);
        result.put("url", webURL);

        return result;
    }
}

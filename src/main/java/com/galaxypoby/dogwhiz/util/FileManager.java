package com.galaxypoby.dogwhiz.util;

import com.galaxypoby.dogwhiz.code.ErrorCode;
import com.galaxypoby.dogwhiz.common.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Component
@Slf4j
public class FileManager {

    @Value("${path.rootURL}")
    private String rootURL;

    public Map<String, String> fileUpload(String folder, MultipartFile file) throws CustomException {
        String rootFolder = "/home";

        String originalFilename = file.getOriginalFilename();
        String extension = Objects.requireNonNull(originalFilename).substring(originalFilename.lastIndexOf('.'));
        String newFilename = UUID.randomUUID().toString() + extension;

        File directory = new File(rootFolder + "/" + folder);
        if (!directory.exists()){
            boolean result = directory.mkdirs();
            if (!result) {
                log.warn("FileManager: " + folder + "경로 생성에 실패하였습니다.");
                throw new CustomException(ErrorCode.FILE_FAIL_MKDIR);
            }
        }

        String savePath = Paths.get(rootFolder, folder, newFilename).toString();
        String webURL = rootURL + "/" + folder + "/" + newFilename;

        File saveFile = new File(savePath);
        try {
            file.transferTo(saveFile);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }

        Map<String, String> result = new HashMap<>();
        result.put("path", savePath);
        result.put("url", webURL);

        return result;
    }
}

package com.galaxypoby.dogwhiz;

import com.galaxypoby.dogwhiz.file.FileManager;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {

    private final FileManager fileManager;

    @PostMapping
    public String fileupload(@RequestParam(name = "file")MultipartFile file) {
        fileManager.uploadFile(file);
        return "결과는!!";
    }
}

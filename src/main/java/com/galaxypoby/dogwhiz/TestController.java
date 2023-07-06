package com.galaxypoby.dogwhiz;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {

    @PostMapping
    public String test1() {
        return "Hello";
    }

    @GetMapping
    public String test2() {
        return "Hello";
    }
}

package com.galaxypoby.dogwhiz.banner;

import com.galaxypoby.dogwhiz.banner.dto.RequestBannerDto;
import com.galaxypoby.dogwhiz.common.CustomException;
import com.galaxypoby.dogwhiz.common.CustomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/banners")
public class BannerApiController {

    private final BannerService bannerService;

    @PostMapping
    public CustomResponse bannerAdd(@RequestPart(name = "request")RequestBannerDto.ResisterDto request,
                                    @RequestPart(name = "file")MultipartFile file) throws CustomException {
        return bannerService.addBanner(request, file);
    }
}

package com.galaxypoby.dogwhiz.admins.banner;

import com.galaxypoby.dogwhiz.admins.banner.dto.RequestBannerDto;
import com.galaxypoby.dogwhiz.common.CustomException;
import com.galaxypoby.dogwhiz.common.CustomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping
    public CustomResponse bannerListExposure() {
        return bannerService.findBannerListExposure();
    }
}

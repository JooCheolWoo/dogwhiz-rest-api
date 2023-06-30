package com.galaxypoby.dogwhiz.banner;

import com.galaxypoby.dogwhiz.banner.dto.RequestBannerDto;
import com.galaxypoby.dogwhiz.banner.dto.ResponseBannerDto;
import com.galaxypoby.dogwhiz.banner.entity.Banner;
import com.galaxypoby.dogwhiz.banner.repository.BannerFileRepository;
import com.galaxypoby.dogwhiz.banner.repository.BannerRepository;
import com.galaxypoby.dogwhiz.code.ErrorCode;
import com.galaxypoby.dogwhiz.common.CustomException;
import com.galaxypoby.dogwhiz.common.CustomResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class BannerService {

    private final BannerRepository bannerRepository;
    private final BannerFileRepository bannerFileRepository;
    private final ModelMapper modelMapper;

    public CustomResponse addBanner(RequestBannerDto.ResisterDto request, MultipartFile file) throws CustomException {
        Banner banner = modelMapper.map(request, Banner.class);


        ResponseBannerDto response = modelMapper.map(banner, ResponseBannerDto.class);

        return new CustomResponse(ErrorCode.OK, response);
    }
}

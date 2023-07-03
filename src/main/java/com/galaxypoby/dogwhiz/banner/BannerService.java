package com.galaxypoby.dogwhiz.banner;

import com.galaxypoby.dogwhiz.banner.dto.RequestBannerDto;
import com.galaxypoby.dogwhiz.banner.dto.ResponseBannerDto;
import com.galaxypoby.dogwhiz.banner.entity.Banner;
import com.galaxypoby.dogwhiz.banner.entity.BannerFile;
import com.galaxypoby.dogwhiz.banner.repository.BannerFileRepository;
import com.galaxypoby.dogwhiz.banner.repository.BannerRepository;
import com.galaxypoby.dogwhiz.code.ErrorCode;
import com.galaxypoby.dogwhiz.common.CustomException;
import com.galaxypoby.dogwhiz.common.CustomResponse;
import com.galaxypoby.dogwhiz.common.fileManager.FileUpDown;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BannerService {

    private final BannerRepository bannerRepository;
    private final ModelMapper modelMapper;
    private final FileUpDown fileUpDown;

    @Transactional
    public CustomResponse addBanner(RequestBannerDto.ResisterDto request, MultipartFile file) throws CustomException {
        Banner banner = modelMapper.map(request, Banner.class);

        log.info("제목 : " + banner.getTitle());

        if (file == null) {
            throw new CustomException(ErrorCode.FILE_NOT_REGISTERED);
        }

        if (!file.getContentType().startsWith("image")) {
            throw new CustomException(ErrorCode.FILE_NOT_IMAGE);
        }

        Map<String ,String> path = fileUpDown.fileUpload("profile", file);

        BannerFile bannerFile = BannerFile.builder()
                .banner(banner)
                .name(file.getOriginalFilename())
                .size(file.getSize())
                .url(path.get("url"))
                .path(path.get("path"))
                .build();

        banner.updateFile(bannerFile);

        bannerRepository.save(banner);

        ResponseBannerDto response = modelMapper.map(banner, ResponseBannerDto.class);

        return new CustomResponse(ErrorCode.OK, response);
    }

    public CustomResponse findBannerListExposure() {
        List<Banner> banners = bannerRepository.findAllByExposureIsTrue();

        List<ResponseBannerDto> response = banners.stream()
                .map(banner -> modelMapper.map(banner, ResponseBannerDto.class))
                .collect(Collectors.toList());

        return new CustomResponse(ErrorCode.OK, response);
    }
}

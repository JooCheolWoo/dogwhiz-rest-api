package com.galaxypoby.dogwhiz.board;

import com.galaxypoby.dogwhiz.board.dto.RequestBoardDto;
import com.galaxypoby.dogwhiz.common.CustomException;
import com.galaxypoby.dogwhiz.common.CustomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/boards")
public class BoardApiController {

    private final BoardService boardService;

    @PostMapping
    public CustomResponse postAdd(@AuthenticationPrincipal UserDetails userDetails,
                                  @RequestPart(name = "request") RequestBoardDto.BoardDto request,
                                  @RequestPart(name = "file", required = false) MultipartFile[] files) throws CustomException {
        return  boardService.addPost(userDetails, request, files);
    }
}

package com.galaxypoby.dogwhiz.board;

import com.galaxypoby.dogwhiz.board.dto.RequestBoardDto;
import com.galaxypoby.dogwhiz.board.dto.ResponseBoardDto;
import com.galaxypoby.dogwhiz.board.entity.Board;
import com.galaxypoby.dogwhiz.board.repository.BoardRepository;
import com.galaxypoby.dogwhiz.code.BoardCode;
import com.galaxypoby.dogwhiz.code.ErrorCode;
import com.galaxypoby.dogwhiz.common.Common;
import com.galaxypoby.dogwhiz.common.CustomException;
import com.galaxypoby.dogwhiz.common.CustomResponse;
import com.galaxypoby.dogwhiz.member.entity.Member;
import com.galaxypoby.dogwhiz.member.repository.MemberRepository;
import com.galaxypoby.dogwhiz.util.FileManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final FileManager fileManager;
    private final ModelMapper modelMapper;

    @Transactional
    public CustomResponse addPost(UserDetails userDetails, RequestBoardDto.BoardDto request, MultipartFile[] files) throws CustomException {
        String email = userDetails.getUsername();

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_EXIST));

        if (request.getCategory().equals(BoardCode.Category.NOTICE.getCode())) {
            Common.checkAdmin(member);
        }

        Board board = Board.builder()
                .memberId(member.getId())
                .category(request.getCategory())
                .subCategory(request.getSubCategory())
                .pinToTop(request.isPinToTop())
                .title(request.getTitle())
                .content(request.getContent())
                .likeCount(0L)
                .viewCount(0L)
                .build();

        boardRepository.save(board);

        ResponseBoardDto.BoardDto response = modelMapper.map(board, ResponseBoardDto.BoardDto.class);
        response.setupWriter(member.getNickname(), member.getImageUrl());

        return new CustomResponse(ErrorCode.OK, response);
    }

    public CustomResponse findPostList(String category) {
        category = category.toUpperCase();

        String categoryCode = BoardCode.Category.nameToCode(category);
        List<Board> postList = boardRepository.findByCategoryAndDeletedAtIsNull(categoryCode);

        List<ResponseBoardDto.BoardDto> response = postList
                .stream()
                .map(post -> modelMapper.map(post, ResponseBoardDto.BoardDto.class))
                .collect(Collectors.toList());

        for (ResponseBoardDto.BoardDto boardDto : response) {
            Member member = memberRepository.findById(boardDto.getMemberId())
                    .orElseThrow();

            boardDto.setupWriter(member.getNickname(), member.getImageUrl());
        }

        return new CustomResponse(ErrorCode.OK, response);
    }
}

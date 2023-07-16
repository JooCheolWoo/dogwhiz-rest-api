package com.galaxypoby.dogwhiz.board;

import com.galaxypoby.dogwhiz.admins.category.entity.Category;
import com.galaxypoby.dogwhiz.admins.category.entity.SubCategory;
import com.galaxypoby.dogwhiz.admins.category.repository.CategoryRepository;
import com.galaxypoby.dogwhiz.admins.category.repository.SubCategoryRepository;
import com.galaxypoby.dogwhiz.board.dto.RequestBoardDto;
import com.galaxypoby.dogwhiz.board.dto.ResponseBoardDto;
import com.galaxypoby.dogwhiz.board.entity.Board;
import com.galaxypoby.dogwhiz.board.repository.BoardCustomRepository;
import com.galaxypoby.dogwhiz.board.repository.BoardRepository;
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
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final FileManager fileManager;
    private final ModelMapper modelMapper;
    private final BoardCustomRepository boardCustomRepository;
    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;

    @Transactional
    public CustomResponse addBoard(UserDetails userDetails, RequestBoardDto.BoardDto request, MultipartFile[] files) throws CustomException {
        String email = userDetails.getUsername();

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_EXIST));


        SubCategory subCategory = subCategoryRepository.findByCategory_NameAndName(request.getCategory(), request.getSubCategory())
                .orElseThrow(() -> new CustomException(ErrorCode.CATEGORY_NOT_EXIST));

        if (subCategory.getCategory().getCudAuth() != null) {
            Common.checkAuth(member, subCategory.getCategory().getCudAuth());
        }

        Board board = Board.builder()
                .member(member)
                .writer(member.getNickname())
                .writerImageUrl(member.getImageUrl())
                .category(subCategory.getCategory())
                .subCategory(subCategory)
                .pinToTop(request.isPinToTop())
                .title(request.getTitle())
                .content(request.getContent())
                .build();

        boardRepository.save(board);

        ResponseBoardDto.BoardDto response = modelMapper.map(board, ResponseBoardDto.BoardDto.class);

        return new CustomResponse(ErrorCode.OK, response);
    }

    public CustomResponse getBoardList(UserDetails userDetails, RequestBoardDto.BoardListRequestDto request, Pageable pageable, String[] sort) throws CustomException {

        String email = userDetails.getUsername();

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_EXIST));


        Category category = categoryRepository.findByName(request.getCategory())
                .orElseThrow(() -> new CustomException(ErrorCode.CATEGORY_NOT_EXIST));

        if (category.getReadAuth() != null) {
            Common.checkAuth(member, category.getReadAuth());
        }

        SubCategory subCategory = null;
        Page<Board> boards = null;
        if (request.getSubCategory() != null && !request.getSubCategory().isEmpty()) {
            subCategory = subCategoryRepository.findByCategory_NameAndName(request.getCategory(), request.getSubCategory())
                    .orElseThrow(() -> new CustomException(ErrorCode.CATEGORY_NOT_EXIST));
            boards = boardCustomRepository.getSubCategoryList(request, subCategory, pageable);
        } else {
            boards = boardCustomRepository.getCategoryList(request, category, pageable);
        }




        Page<ResponseBoardDto.BoardListDto> list = boards.map(board -> modelMapper.map(board, ResponseBoardDto.BoardListDto.class));

        List<ResponseBoardDto.BoardListDto> fixedResponse = new ArrayList<>();
        if (request.getSearch().isEmpty()) {
            List<Board> fixedList = null;
            if (request.getSubCategory() != null && !request.getSubCategory().isEmpty()) {
                fixedList = boardRepository.findBySubCategory_IdAndPinToTopIsTrueAndDeletedAtIsNull(subCategory.getId());
            } else {
                fixedList = boardRepository.findByCategory_IdAndPinToTopIsTrueAndDeletedAtIsNull(category.getId());
            }
            fixedResponse = modelMapper.map(fixedList, new TypeToken<List<ResponseBoardDto.BoardListDto>>() {}.getType());
        }

        ResponseBoardDto.BoardListResultDto response = ResponseBoardDto.BoardListResultDto.builder()
                .list(list)
                .fixedList(fixedResponse)
                .build();

        return new CustomResponse(ErrorCode.OK, response);
    }
}

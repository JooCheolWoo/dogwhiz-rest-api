package com.galaxypoby.dogwhiz.board.repository;

import com.galaxypoby.dogwhiz.board.dto.RequestBoardDto;
import com.galaxypoby.dogwhiz.board.entity.Board;
import com.galaxypoby.dogwhiz.board.entity.QBoard;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardCustomRepository {
    private final EntityManager entityManager;

    public Page<Board> getList(RequestBoardDto.BoardListRequestDto request, Pageable pageable) {
        String category = request.getCategory();
        String subCategory = request.getSubCategory();
        String type = request.getType();
        String search = request.getSearch();

        JPAQuery<Board> query = new JPAQuery<>(entityManager)
                .select(QBoard.board)
                .from(QBoard.board)
                .where(QBoard.board.category.eq(category)
                        .and(QBoard.board.subCategory.eq(subCategory)
                                .and(QBoard.board.deletedAt.isNull())));

        Long count = new JPAQuery<>(entityManager)
                .select(QBoard.board.count())
                .from(QBoard.board)
                .where(QBoard.board.category.eq(category)
                        .and(QBoard.board.subCategory.eq(subCategory)
                                .and(QBoard.board.deletedAt.isNull())))
                .where(contentSearch(type, search))
                .orderBy(contentOrderBy(pageable.getSort().toString()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchOne();

        List<Board> results = query
                .where(contentSearch(type, search))
                .orderBy(contentOrderBy(pageable.getSort().toString()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(results, pageable, count);
    }

    private BooleanExpression contentSearch(String type, String search) {
        if (search.isEmpty()) {
            return null;
        } else if (type.equals("title")) {
            return QBoard.board.title.like("%%" + search + "%%");
        } else if (type.equals("writer")) {
            return QBoard.board.writer.like("%%" + search + "%%");
        } else if (type.equals("content")) {
            return QBoard.board.content.like("%%" + search + "%%");
        }
        return null;
    }

    private OrderSpecifier<LocalDateTime> contentOrderBy(String sort) {
        String sortText = sort.replaceAll(" ", "");
        String[] sortArray = sortText.split(":");
        String column = sortArray[0].toUpperCase();
        String orderBy = sortArray[1].toUpperCase();

        if (column.equals("") || orderBy.equals("")) {
            return QBoard.board.createdAt.desc();
        } else if (orderBy.equals("ASC") && column.equals("CREATEDAT")) {
            return QBoard.board.createdAt.asc();
        } else {
            return QBoard.board.createdAt.desc();
        }
    }
}

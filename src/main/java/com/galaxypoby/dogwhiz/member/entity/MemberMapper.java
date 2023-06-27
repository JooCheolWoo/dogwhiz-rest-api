package com.galaxypoby.dogwhiz.member.entity;

import com.galaxypoby.dogwhiz.member.dto.RequestMemberDto;
import com.galaxypoby.dogwhiz.member.dto.ResponseMemberDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MemberMapper {

    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    // entity -> dto
    ResponseMemberDto.MemberDto toResponseMemberDto(Member member);
    List<ResponseMemberDto.MemberDto> toResponseMemberDtoList(List<Member> members);

    // dto -> entity
    Member requestMemberDtotoMember(RequestMemberDto.MemberDto memberDto);
}

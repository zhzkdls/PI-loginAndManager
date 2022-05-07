package com.login.loginandmanager.member.service;

import com.login.loginandmanager.member.dto.MemberDto;
import com.login.loginandmanager.member.model.Member;

import java.util.List;

public interface MemberService {

    //저장 수정
    int saveMember(MemberDto memberDto);
    int updateMember(Long id, MemberDto memberDto);

    //조회
    List<Member> findAllMember();
    Member findOndMember(Long id);

    //삭제 및 복구
    int deleteMember(Long id);
    int undeleteMember(Long id);

    //중복체크 및 찾기
    int duplicateCheckEmail(String email);
    int duplicateCheckId(String userId);
    int duplicateCheckPhone(String phone);
    String findUserId(String userEmail, String userPhone);
}

package com.login.loginandmanager.member.repository;

import com.login.loginandmanager.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByUserId(String userId);
    Member findByUserEmail(String email);
    Member findByUserPhone(String phone);
    Member findByUserEmailAndUserPhone(String userEmail, String userPhone); //아이디 찾기
}

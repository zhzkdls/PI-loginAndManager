package com.login.loginandmanager.login.service;

import com.login.loginandmanager.member.dto.MemberDto;
import com.login.loginandmanager.member.model.Member;
import com.login.loginandmanager.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Slf4j
@RequiredArgsConstructor
@Service
public class LoginServiceImpl implements LoginService{


    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean loginMember(MemberDto memberDto) {

        Member member = memberRepository.findByUserId(memberDto.getUserId());
        if(member != null) {
            return passwordEncoder.matches(memberDto.getUserPassword(), member.getUserPassword());
        }
        return false;
    }

}

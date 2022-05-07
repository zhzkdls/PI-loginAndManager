package com.login.loginandmanager.login.controller;

import com.login.loginandmanager.login.service.LoginService;
import com.login.loginandmanager.member.dto.MemberDto;
import com.login.loginandmanager.member.model.Member;
import com.login.loginandmanager.member.repository.MemberRepository;
import com.login.loginandmanager.member.service.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/member")
public class LoginController {

    private final LoginService loginService;
    private final MemberServiceImpl memberService;
    private final MemberRepository memberRepository;

    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    public Member loginMember(@RequestBody MemberDto memberDto){
        if(loginService.loginMember(memberDto)){
            return memberRepository.findByUserId(memberDto.getUserId());
        }else{
            return null;
        }
    }

    @PostMapping("/logout")
    public String userLogout(@ModelAttribute("member") Member member, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "userRemove";
    }

    @PostMapping("/withdrawal")
    public String userWithdrawal(@ModelAttribute("member") Member member, HttpServletRequest request) {
        memberService.deleteMember(member.getId());
        HttpSession session = request.getSession();
        session.invalidate();
        return "userRemove";
    }
}

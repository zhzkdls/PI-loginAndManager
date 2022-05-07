package com.login.loginandmanager.member.controller;

import com.login.loginandmanager.member.dto.MemberDto;
import com.login.loginandmanager.member.model.Member;
import com.login.loginandmanager.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @RequestMapping(value = "/save", method = {RequestMethod.GET, RequestMethod.POST})
    public String saveMember(@RequestBody MemberDto memberDto){
        int i = memberService.saveMember(memberDto);
        if(i == 2){
            log.info("이메일 중복");
            return "해당 이메일로 가입된 계정이 있습니다"; //이메일 중복
        }else if(i == 3){
            log.info("아이디 중복");
            return "해당 아이디로 가입된 계정이 있습니다"; //아이디 중복
        }else if(i == 4){
            log.info("전화번호 중복");
            return "해당 전화번호로 가입된 계정이 있습니다"; // 전화번호 중복
        }else if(i == 1){
            return "Success";
        }else{
            return "fail";
        }
    }

    @RequestMapping(value="/update/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public String updateMember(@PathVariable Long id, @RequestBody MemberDto memberDto){
        if(memberService.updateMember(id, memberDto) == 1){
            return "수정 완료";
        }else{
            return "수정 실패";
        }
    }

    @RequestMapping(value="/findMemberId", method = {RequestMethod.GET, RequestMethod.POST})
    public String findMemberId(@RequestBody Member member){
            return memberService.findUserId(member.getUserEmail(), member.getUserPhone());
    }

    @GetMapping("/getAll")
    public List<Member> getAllMember(){
        return memberService.findAllMember();
    }

    @RequestMapping(value="/findMember/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public Member findMember(@PathVariable Long id){
        return memberService.findOndMember(id);
    }

    @RequestMapping(value="/delete/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public String deleteMember(@PathVariable Long id){
        if(memberService.deleteMember(id) == 1){
            return "삭제 완료";
        }else{
            return "삭제 실패 : 해당 유저가 없음 아이디 확인";
        }
    }

    @RequestMapping(value="/undelete/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public String undeleteMember(@PathVariable Long id){
        if(memberService.undeleteMember(id) == 1){
            return "복구 완료";
        }else{
            return "복구 실패 : 해당 유저가 없음 아이디 확인";
        }
    }
}

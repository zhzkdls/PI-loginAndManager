package com.login.loginandmanager.member.service;

import com.login.loginandmanager.member.dto.MemberDto;
import com.login.loginandmanager.member.model.Member;
import com.login.loginandmanager.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public int saveMember(MemberDto memberDto) {
        try {
            int i, j, k;
            i = duplicateCheckEmail(memberDto.getUserEmail());
            j = duplicateCheckId(memberDto.getUserId());
            k = duplicateCheckPhone(memberDto.getUserPhone());

            if(i == 1 && j == 1 && k == 1){
                LocalDateTime ldt = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
                String formatedldt = ldt.format(formatter);

                String rawPassword = memberDto.getUserPassword();
                String encPassword = passwordEncoder.encode(rawPassword);

                Member member = Member.builder()
                        .id(null)
                        .userName(memberDto.getUserName())
                        .userId(memberDto.getUserId())
                        .password(encPassword)
                        .email(memberDto.getUserEmail())
                        .phone(memberDto.getUserPhone())
                        .role(memberDto.getRole())
                        .createDate(formatedldt)
                        .stat(1)
                        .build();
                memberRepository.save(member);
                log.info("SAVE MEMBER : {} ", memberDto);
                return 1;
            }else{
                if(i == 0){
                    return 2;
                }else if(j==0){
                    return 3;
                }else if(k==0){
                    return 4;
                }
                return 0;
            }
        }catch (Exception e){
            log.error("ERROR : {}",e.getMessage());
            return 0;
        }
    }

    @Override
    public int updateMember(Long id, MemberDto memberDto) {
        try {
            if(memberRepository.findById(id).isPresent()){
                memberRepository.save(
                        Member.builder()
                                .id(id)
                                .userName(memberDto.getUserName())
                                .userId(memberDto.getUserId())
                                .password(memberDto.getUserPassword())
                                .email(memberDto.getUserEmail())
                                .phone(memberDto.getUserPhone())
                                .role(memberDto.getRole())
                                .createDate(memberDto.getCreateDate())
                                .stat(1)
                                .build()
                );
                log.info("UPDATE MEMBER : {} ", memberDto);
                return 1;
            }else{
                log.info("EMPTY MEMBER");
                return 0;
            }
        }catch (Exception e){
            log.error("ERROR : {}",e.getMessage());
            return 0;
        }
    }

    @Override
    public int duplicateCheckEmail(String email) {
        try{
            if(memberRepository.findByUserEmail(email) == null){
                return 1;
            }else{
                log.info("이메일 중복!");
                return 0;
            }
        }catch (Exception e){
            log.error("ERROR : {}", e.getMessage());
            return 0;
        }
    }

    @Override
    public int duplicateCheckId(String userId) {
        try{
            if(memberRepository.findByUserId(userId) == null){
                return 1;
            }else{
                log.info("아이디 중복!");
                return 0;
            }
        }catch (Exception e){
            log.error("ERROR : {}", e.getMessage());
            return 0;
        }
    }

    @Override
    public int duplicateCheckPhone(String phone) {
        try{
            if(memberRepository.findByUserPhone(phone) == null){
                return 1;
            }else{
                log.info("전화번호 중복!");
                return 0;
            }
        }catch (Exception e){
            log.error("ERROR : {}", e.getMessage());
            return 0;
        }
    }

    @Override
    public String findUserId(String userEmail, String userPhone) {
        try{
            if(memberRepository.findByUserEmailAndUserPhone(userEmail, userPhone) != null){
                log.info("findUser : {} ", memberRepository.findByUserEmailAndUserPhone(userEmail, userPhone));
                return memberRepository.findByUserEmailAndUserPhone(userEmail, userPhone).getUserId();
            }
        }catch (Exception e){
            log.error("ERRPR : {} ", e.getMessage());
            return null;
        }
        return null;
    }

    @Override
    public List<Member> findAllMember() {
        log.info("Member List ");
        return memberRepository.findAll();
    }

    @Override
    public Member findOndMember(Long id) {
        try {
            if(memberRepository.findById(id).isPresent()){
                return memberRepository.findById(id).get();
            }else{
                log.info("해당 아이디의 유저 없음.");
            }
        }catch (Exception e){
            log.error("ERROR : {} ", e.getMessage());
        }
        return null;
    }

    @Override
    public int deleteMember(Long id) {
        try {
            if(memberRepository.findById(id).isEmpty()){
                log.info("해당 아이디의 유저 없음.");
                return 0;
            }else{
                memberRepository.findById(id).get().setStat(0);
                return 1;
            }
        }catch (Exception e){
            log.error("ERROR : {} ", e.getMessage());
            return 0;
        }
    }

    @Override
    public int undeleteMember(Long id) {
        try {
            if(memberRepository.findById(id).isEmpty()){
                log.info("해당 아이디의 유저 없음.");
                return 0;
            }else{
                memberRepository.findById(id).get().setStat(1);
                return 1;
            }
        }catch (Exception e){
            log.error("ERROR : {} ", e.getMessage());
            return 0;
        }
    }
}

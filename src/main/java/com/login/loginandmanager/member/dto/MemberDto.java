package com.login.loginandmanager.member.dto;

import lombok.Data;

@Data
public class MemberDto {

    private String userId, userPassword;
    private String userName, createDate, oauth, userEmail, userPhone, role;
    private int stat;
}

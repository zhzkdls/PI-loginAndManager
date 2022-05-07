package com.login.loginandmanager.member.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Member{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 20)
    private String userName;
    @Column(nullable = false, length = 25, unique = true)
    private String userId;
    @Column(nullable = false)
    private String userPassword;
    @Column(nullable = false, length = 100)
    private String userEmail;
    @Column(nullable = false, length = 20)
    private String userPhone;
    private String createDate;
    @Setter
    private int stat;

    private String role;

    @Builder
    public Member(Long id, String userName, String userId, String password, String email, String phone, String role, String createDate, int stat) {
        this.id = id;
        this.userName = userName;
        this.userId = userId;
        this.userPassword = password;
        this.userEmail = email;
        this.userPhone = phone;
        this.role = role;
        this.createDate = createDate;
        this.stat = stat;
    }

}

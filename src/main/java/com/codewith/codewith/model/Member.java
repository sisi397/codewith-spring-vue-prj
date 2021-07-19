package com.codewith.codewith.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long user_id;

    @Column(length = 50, nullable = false)
    private String id;

    @Column(length = 50, nullable = false)
    private String nickname;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 50, nullable = false)
    private String email;

    @Column(length = 100, nullable = false)
    private String password;

    @Builder
    public Member(Long user_id, String id, String nickname, String name, String email, String password) {
        this.user_id = user_id;
        this.id = id;
        this.nickname = nickname;
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
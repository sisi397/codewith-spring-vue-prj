
package com.codewith.codewith.model;

import com.codewith.codewith.dto.MemberDto;
import com.codewith.codewith.dto.StageIngRequestDto;
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
    private Long id;

    @Column(length = 50, nullable = false)
    private String userId;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 50, nullable = false)
    private String email;

    @Column(length = 100, nullable = false)
    private String password;

    @Column
    private String filename;

    @Builder
    public Member(Long id, String userId, String name, String email, String password, String filename) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.filename = filename;
    }

    public void update(String password) {
        System.out.println("비밀번호 update 시작");
        this.password = password;
    }

    public void updateFile(String file){
        System.out.println("파일 업로드 update");
        this.filename = file;
    }
}
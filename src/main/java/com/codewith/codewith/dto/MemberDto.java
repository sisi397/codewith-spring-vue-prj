package com.codewith.codewith.dto;

import com.codewith.codewith.model.Member;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberDto {
    private Long id;
    private String userId;
    private String nickname;
    private String name;
    private String email;
    private String password;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public Member toEntity(){
        return Member.builder()
                .userId(userId)
                .id(id)
                .name(name)
                .nickname(nickname)
                .email(email)
                .password(password)
                .build();
    }

    @Builder
    public MemberDto(Long id, String userId, String nickname, String name, String email, String password) {
        this.id = id;
        this.userId = userId;
        this.nickname = nickname;
        this.name = name;
        this.email = email;
        this.password = password;
    }
}

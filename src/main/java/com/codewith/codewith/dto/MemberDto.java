package com.codewith.codewith.dto;

import com.codewith.codewith.model.Member;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberDto {
    private Long user_id;
    private String id;
    private String nickname;
    private String name;
    private String email;
    private String password;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public Member toEntity(){
        return Member.builder()
                .user_id(user_id)
                .id(id)
                .name(name)
                .nickname(nickname)
                .email(email)
                .password(password)
                .build();
    }

    @Builder
    public MemberDto(Long user_id, String id, String nickname, String name, String email, String password) {
        this.user_id = user_id;
        this.id = id;
        this.nickname = nickname;
        this.name = name;
        this.email = email;
        this.password = password;
    }
}

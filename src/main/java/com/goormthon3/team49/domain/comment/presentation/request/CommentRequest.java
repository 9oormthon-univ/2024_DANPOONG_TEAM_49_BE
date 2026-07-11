package com.goormthon3.team49.domain.comment.presentation.request;

import lombok.Getter;

@Getter
public record CommentRequest(String content,Long kakaoId) {
    public static CommentRequest of(String content, Long kakaoId) {
        return new CommentRequest(content,kakaoId);
    }
}

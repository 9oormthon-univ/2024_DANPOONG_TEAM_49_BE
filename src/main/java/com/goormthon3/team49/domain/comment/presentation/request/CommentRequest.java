package com.goormthon3.team49.domain.comment.presentation.request;

public record CommentRequest(String content) {
    public static CommentRequest of(String content) {
        return new CommentRequest(content);
    }
}

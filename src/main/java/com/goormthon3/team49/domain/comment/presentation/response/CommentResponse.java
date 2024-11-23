package com.goormthon3.team49.domain.comment.presentation.response;

import com.goormthon3.team49.domain.comment.domain.Comment;
import com.goormthon3.team49.domain.user.domain.User;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.format.DateTimeFormatter;

@Builder
public record CommentResponse(
        Long comment_id,
        Long user_id,
        String username,
        String content,
        @DateTimeFormat(pattern="yyyy-MM-dd")
        String createdAt
) {
    public static CommentResponse of(Comment comment) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return CommentResponse.builder()
                .comment_id(comment.getCommentId())
                .username(comment.getUsername())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt().format(formatter))
                .build();
    }
}

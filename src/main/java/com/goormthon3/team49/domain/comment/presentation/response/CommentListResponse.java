package com.goormthon3.team49.domain.comment.presentation.response;

import com.goormthon3.team49.domain.comment.domain.Comment;
import lombok.Builder;
import java.util.List;

@Builder
public record CommentListResponse(
        Long product_id,
        Long comment_count,
        List<CommentResponse> comments
) {
    public static CommentListResponse of(Long product_id, Long comment_count, List<Comment> comments) {
        return CommentListResponse.builder()
                .product_id(product_id)
                .comment_count(comment_count)
                .comments(comments.stream()
                        .map(comment -> CommentResponse.of(comment, comment.getUser())) // User 객체를 제공
                        .toList())
                .build();
    }
}

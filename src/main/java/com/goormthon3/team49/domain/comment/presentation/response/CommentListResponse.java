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
                        .map(CommentResponse::of) // CommentResponse.of(comment) -> 이 부분 수정
                        .toList())  // 끝에 toList()를 제대로 닫기
                .build();
    }
}


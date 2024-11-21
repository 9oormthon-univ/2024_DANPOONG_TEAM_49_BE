package com.goormthon3.team49.domain.comment.domain;

import java.util.List;

public interface CommentRepository {
    Comment save(Comment comment);
    List<Comment> findByProductId(Long productId);
}

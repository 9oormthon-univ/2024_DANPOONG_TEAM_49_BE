package com.goormthon3.team49.domain.comment.infrastucture;

import com.goormthon3.team49.domain.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaCommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByProductId(Long productId);
}

package com.goormthon3.team49.domain.comment.infrastucture;

import com.goormthon3.team49.domain.comment.domain.Comment;
import com.goormthon3.team49.domain.comment.domain.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {
    private final JpaCommentRepository jpaCommentRepository;

    @Override
    public Comment save(Comment comment) {
        return jpaCommentRepository.save(comment);
    }

    @Override
    public List<Comment> findByProductId(Long product_id) {
        return jpaCommentRepository.findByProductId(product_id);
    }
}

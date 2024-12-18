package com.goormthon3.team49.domain.comment.application;

import com.goormthon3.team49.domain.comment.domain.Comment;
import com.goormthon3.team49.domain.comment.domain.CommentRepository;
import com.goormthon3.team49.domain.comment.presentation.request.CommentRequest;
import com.goormthon3.team49.domain.comment.presentation.response.CommentListResponse;
import com.goormthon3.team49.domain.comment.presentation.response.CommentResponse;
import com.goormthon3.team49.domain.user.application.UserLoginService;
import com.goormthon3.team49.domain.user.domain.User;
import com.goormthon3.team49.domain.user.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Comments;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserLoginService userLoginService;
    private final UserRepository userRepository;

    @Transactional
    public CommentResponse createComment(Long product_id, CommentRequest request, String username) {
        Comment comment=Comment.create(request.content(),username,product_id);
        commentRepository.save(comment);
        return CommentResponse.of(comment);
    }

    @Transactional
    public CommentListResponse getComment(Long product_id) {
        List<Comment> comments=commentRepository.findByProductId(product_id);
        Long comment_count=Long.valueOf(comments.size());
        return CommentListResponse.of(product_id,comment_count,comments);
    }

}

package com.goormthon3.team49.domain.comment.presentation;

import com.goormthon3.team49.domain.comment.application.CommentService;
import com.goormthon3.team49.domain.comment.presentation.request.CommentRequest;
import com.goormthon3.team49.domain.comment.presentation.response.CommentListResponse;
import com.goormthon3.team49.domain.comment.presentation.response.CommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    @ResponseStatus(CREATED)
    @PostMapping("/{productId}/createComment")
    public ResponseEntity<CommentResponse> createReview(
            @PathVariable Long productId,
             @RequestBody CommentRequest request
    ) {
        CommentResponse response = commentService.createComment(productId,request);
        return ResponseEntity.status(CREATED).body(response);
    }


    @GetMapping("/{productId}")
    public ResponseEntity<CommentListResponse> getReview(
            @PathVariable Long productId
    ) {
        CommentListResponse response = commentService.getComment(productId);
        return ResponseEntity.status(OK).body(response);
    }

}

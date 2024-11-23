package com.goormthon3.team49.domain.comment.presentation;

import com.goormthon3.team49.domain.comment.application.CommentService;
import com.goormthon3.team49.domain.comment.presentation.request.CommentRequest;
import com.goormthon3.team49.domain.comment.presentation.response.CommentListResponse;
import com.goormthon3.team49.domain.comment.presentation.response.CommentResponse;
import com.goormthon3.team49.domain.user.application.UserLoginService;
import com.goormthon3.team49.domain.user.domain.User;
import jakarta.servlet.http.HttpServletRequest;
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
    private final UserLoginService userLoginService;

    @ResponseStatus(CREATED)
    @PostMapping("/{productId}/createComment")
    public ResponseEntity<CommentResponse> createReview(
            @PathVariable Long productId,
            @RequestBody CommentRequest request,
            HttpServletRequest httpServletRequest
    ) {
        String authHeader = httpServletRequest.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Authorization header is missing or invalid");
        }

        String token = authHeader.substring(7);
        Long kakaouserID=userLoginService.getKakaoUserIdFromAccessToken(token);
        User user=userLoginService.findUserByFromKakaoUserId(kakaouserID);
        CommentResponse response = commentService.createComment(productId, request, user);

        return ResponseEntity.status(CREATED).body(response);
    }


    @GetMapping("/{productId}")
    public ResponseEntity<CommentListResponse> getReview(
            @PathVariable Long productId
    ) {
        CommentListResponse response = commentService.getComment(productId);
        return ResponseEntity.status(OK).body(response);
    }


    @GetMapping("/who")
    public String who(
            HttpServletRequest httpServletRequest
    ) {
        String authHeader = httpServletRequest.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Authorization header is missing or invalid");
        }

        String token = authHeader.substring(7);
        Long kakaouserID=userLoginService.getKakaoUserIdFromAccessToken(token);
        User user=userLoginService.findUserByFromKakaoUserId(kakaouserID);
        return user.getUserName();
    }
}

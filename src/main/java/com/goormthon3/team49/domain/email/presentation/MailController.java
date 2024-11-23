package com.goormthon3.team49.domain.email.presentation;

import com.goormthon3.team49.domain.email.application.MailService;
import com.goormthon3.team49.domain.user.application.UserLoginService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class MailController {

    private final MailService mailService;
    private final UserLoginService userLoginService;

    //이메일로 인증코드 발송
    @PostMapping("/email")
    public ResponseEntity<?> sendEmail(@RequestBody MailDto mailDto) throws MessagingException {

        mailService.sendVerificationEmail(mailDto.getEmail());

        return ResponseEntity.ok("인증 코드가 이메일로 전송되었습니다.");
    }

    //인증코드 검증
    @PostMapping("/email/verify")
    public ResponseEntity<String> verifyCodeAndUpdateEmail(@RequestBody MailVerifyDto verifyDto, @RequestHeader("Authorization") String authorization) {

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing or invalid Authorization header");
        }

        String accessToken = authorization.substring(7);
        Long kakaoUserId = userLoginService.getKakaoUserIdFromAccessToken(accessToken);

        try {
            mailService.verifyAndUpdateEmail(verifyDto.getEmail(), verifyDto.getAuthCode(), String.valueOf(kakaoUserId));

            return ResponseEntity.ok("이메일 인증 및 갱신이 완료되었습니다.");
        } catch (ResponseStatusException ex) {
            return ResponseEntity.status(ex.getStatusCode()).body(ex.getReason());
        }
    }
}

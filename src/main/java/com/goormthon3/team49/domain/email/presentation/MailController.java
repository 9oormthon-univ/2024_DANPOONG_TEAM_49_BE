package com.goormthon3.team49.domain.email.presentation;

import com.goormthon3.team49.domain.email.application.MailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class MailController {

    private final MailService mailService;

    //이메일로 인증코드 발송
    @PostMapping("/email")
    public ResponseEntity<?> sendEmail(@RequestBody MailDto mailDto) throws MessagingException {

        mailService.sendVerificationEmail(mailDto.getEmail());

        return ResponseEntity.ok("인증 코드가 이메일로 전송되었습니다.");
    }

    //인증코드 검증
    @PostMapping("/email/verify")
    public ResponseEntity<String> verifyCode(@RequestBody MailVerifyDto verifyDto) {

        try {
            mailService.verifyAuthCode(verifyDto.getEmail(), verifyDto.getAuthCode());

            return ResponseEntity.ok("인증이 완료되었습니다.");
        } catch (ResponseStatusException ex) {
            return ResponseEntity.status(ex.getStatusCode()).body(ex.getReason());
        }
    }
}

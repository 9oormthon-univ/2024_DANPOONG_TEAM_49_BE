package com.goormthon3.team49.domain.email.presentation;

import com.goormthon3.team49.domain.email.application.MailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class MailController {

    private final MailService mailService;

    @ResponseBody
    @PostMapping("/email") // 이 부분은 각자 바꿔주시면 됩니다.
    public String emailCheck(@RequestBody MailDto mailDTO) throws MessagingException, UnsupportedEncodingException {
        String authCode = mailService.sendSimpleMessage(mailDTO.getEmail());

        return authCode; // Response body에 값을 반환
    }
}

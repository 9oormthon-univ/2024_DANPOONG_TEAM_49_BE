package com.goormthon3.team49.domain.email.application;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class MailService {

    @Value("${EMAIL}")
    private String email;

    private final JavaMailSender javaMailSender;
    private final String senderEmail = email;
    private final RedisTemplate<String, String> redisTemplate;

    // 랜덤으로 숫자 생성
    public String createNumber() {
        Random random = new Random();
        StringBuilder key = new StringBuilder();

        for (int i = 0; i < 8; i++) { // 인증 코드 8자리
            int index = random.nextInt(3); // 0~2까지 랜덤, 랜덤값으로 switch문 실행

            switch (index) {
                case 0 -> key.append((char) (random.nextInt(26) + 97)); // 소문자
                case 1 -> key.append((char) (random.nextInt(26) + 65)); // 대문자
                case 2 -> key.append(random.nextInt(10)); // 숫자
            }
        }
        return key.toString();
    }

    public MimeMessage createMail(String mail, String number) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();

        message.setFrom(senderEmail);
        message.setRecipients(MimeMessage.RecipientType.TO, mail);
        message.setSubject("이메일 인증");
        String body = "";
        body += "<h3>요청하신 인증 번호입니다.</h3>";
        body += "<h1>" + number + "</h1>";
        body += "<h3>감사합니다.</h3>";
        message.setText(body, "UTF-8", "html");

        return message;
    }

    //인증코드 생성 및 Redis 저장
    public void saveAuthCodeToRedis(String email, String authCode) {
        redisTemplate.opsForValue().set(email, authCode, 3, TimeUnit.MINUTES); //TTL 3분
    }

    //메일 발송
    public void sendVerificationEmail(String sendEmail) throws MessagingException {
        String number = createNumber();

        MimeMessage message = createMail(sendEmail, number);
        try {
            javaMailSender.send(message);
        } catch (MailException e) {
            e.printStackTrace();

            throw new IllegalArgumentException("메일 발송 중 오류가 발생했습니다.");
        }

        saveAuthCodeToRedis(sendEmail, number);
    }

    //인증코드 검증
    public boolean verifyAuthCode(String email, String inputCode) {

        String storedCode = redisTemplate.opsForValue().get(email);

        if (storedCode == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "인증 코드가 만료되었거나 존재하지 않습니다.");
        }

        if (!storedCode.equals(inputCode)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "인증 코드가 일치하지 않습니다.");
        }

        redisTemplate.delete(email);

        return true;
    }
}

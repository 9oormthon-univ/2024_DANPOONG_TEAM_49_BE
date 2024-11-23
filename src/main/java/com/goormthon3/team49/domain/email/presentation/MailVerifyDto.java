package com.goormthon3.team49.domain.email.presentation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailVerifyDto {
    private String email;
    private String authCode;
}

package com.goormthon3.team49.domain.user.presentation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class UserLoginDto {

    public String accessToken;
    public String refreshToken;
}

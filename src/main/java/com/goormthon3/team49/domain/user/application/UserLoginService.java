package com.goormthon3.team49.domain.user.application;


import com.goormthon3.team49.domain.user.domain.User;
import com.goormthon3.team49.domain.user.infrastructure.UserRepository;
import com.goormthon3.team49.domain.user.presentation.UserInfoResponseDto;
import com.goormthon3.team49.domain.user.presentation.UserLoginResponseDto;
import io.netty.handler.codec.http.HttpHeaderValues;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class UserLoginService {

    private String clientId;
    private final UserRepository userRepository;
    private final String KAUTH_TOKEN_URL_HOST;
    private final String KAUTH_USER_URL_HOST;

    @Autowired
    public UserLoginService(@Value("${kakao.client_id}") String clientId, UserRepository userRepository) {
        this.clientId = clientId;
        this.userRepository = userRepository;
        this.KAUTH_TOKEN_URL_HOST = "https://kauth.kakao.com";
        this.KAUTH_USER_URL_HOST = "https://kapi.kakao.com";
    }

    public String getAccessTokenFromKakao(String code) {

        UserLoginResponseDto userLoginResponseDto = WebClient.create(KAUTH_TOKEN_URL_HOST).post()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .path("/oauth/token")
                        .queryParam("grant_type", "authorization_code")
                        .queryParam("client_id", clientId)
                        .queryParam("code", code)
                        .build(true))
                .header(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString())
                .retrieve()
                //TODO : Custom Exception
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new RuntimeException("Invalid Parameter")))
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> Mono.error(new RuntimeException("Internal Server Error")))
                .bodyToMono(UserLoginResponseDto.class)
                .block();

        return userLoginResponseDto.getAccessToken();
    }

    public UserInfoResponseDto getUserInfo(String accessToken) {

        UserInfoResponseDto userInfo = WebClient.create(KAUTH_USER_URL_HOST)
                .get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .path("/v2/user/me")
                        .build(true))
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken) //access token 인가
                .header(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString())
                .retrieve()
                //TODO : Custom Exception
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new RuntimeException("Invalid Parameter")))
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> Mono.error(new RuntimeException("Internal Server Error")))
                .bodyToMono(UserInfoResponseDto.class)
                .block();

        return userInfo;
    }

    public User saveOrUpdateUser(UserInfoResponseDto userInfo) {

        Long kakaoUserId = userInfo.getId();
        String nickname = userInfo.getKakaoAccount().getProfile().getNickName();
        String profileImageUri = userInfo.getKakaoAccount().getProfile().getProfileImageUrl();

        User user = userRepository.findByKakaoUserId(kakaoUserId)
                .orElseGet(() -> new User(
                        null,
                        kakaoUserId,
                        nickname,
                        null,
                        null,
                        null,
                        true,
                        null,
                        profileImageUri,
                        null
                ));

        user = new User(
                user.getUserId(),
                user.getKakaoUserId(),
                nickname,
                user.getSchool(),
                user.getLatitude(),
                user.getLongitude(),
                user.getActivated(),
                user.getCreatedAt(),
                profileImageUri,
                user.getEmail()
        );

        return userRepository.save(user);
    }

    public Long getKakaoUserIdFromAccessToken(String accessToken) {

        UserInfoResponseDto userInfo = getUserInfo(accessToken);
        Long kakaoUserId = userInfo.getId();

        return kakaoUserId;
    }

    public User findUserByFromKakaoUserId(Long kakaoUserId) {
        return userRepository.findByKakaoUserId(kakaoUserId)
                .orElseThrow(() -> new RuntimeException("User not found with Kakao User ID: " + kakaoUserId));
    }




}

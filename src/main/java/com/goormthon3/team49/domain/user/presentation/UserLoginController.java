package com.goormthon3.team49.domain.user.presentation;

import com.goormthon3.team49.domain.user.application.UserLoginService;
import com.goormthon3.team49.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserLoginController {

    private final UserLoginService userLoginService;

    @GetMapping("/callback")
    public ResponseEntity<?> callback(@RequestParam("code") String code) {
        String accessToken = userLoginService.getAccessTokenFromKakao(code);

        UserInfoResponseDto userInfo = userLoginService.getUserInfo(accessToken);

        //User 로그인, 또는 회원가입 로직 추가
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/code/kakao")
    public ResponseEntity<?> getAccessToken(@RequestBody Map<String, String> request) {
        String code = request.get("code");
        if (code == null) {
            return new ResponseEntity<>("Missing 'code' parameter", HttpStatus.BAD_REQUEST);
        }

        String accessToken = userLoginService.getAccessTokenFromKakao(code);
        return ResponseEntity.ok(Map.of("access_token", accessToken));
    }

    @GetMapping("/token")
    public ResponseEntity<?> getUserInfo(@RequestHeader("Authorization") String authorization) {

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            return new ResponseEntity<>("Missing or invalid Authorization header", HttpStatus.UNAUTHORIZED);
        }

        String accessToken = authorization.substring(7);
        UserInfoResponseDto userInfo = userLoginService.getUserInfo(accessToken);

        return ResponseEntity.ok(Map.of(
                "user_id", userInfo.getId(),
                "nickname", userInfo.getKakaoAccount().getProfile().getNickName(),
                "profile_image_url", userInfo.getKakaoAccount().getProfile().getProfileImageUrl()
        ));
    }

    @PostMapping("/token/save")
    public ResponseEntity<?> saveUserInfo(@RequestHeader("Authorization") String authorization) {

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            return new ResponseEntity<>("Missing or invalid Authorization header", HttpStatus.UNAUTHORIZED);
        }

        String accessToken = authorization.substring(7);
        UserInfoResponseDto userInfo = userLoginService.getUserInfo(accessToken);

        User savedUser = userLoginService.saveOrUpdateUser(userInfo);

        Map<String, Object> response = Map.of(
                "message", "User info saved successfully",
                "user_info", Map.of(
                        "kakao_user_id", savedUser.getKakaoUserId(),
                        "nickname", savedUser.getUserName(),
                        "profile_image_uri", savedUser.getProfileImageUri()
                )
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/kakao-user-id")
    public ResponseEntity<?> getKakaoUserId(@RequestHeader("Authorization") String authorization) {

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            return new ResponseEntity<>("Missing or invalid Authorization header", HttpStatus.UNAUTHORIZED);
        }

        String accessToken = authorization.substring(7);
        Long kakaoUserId = userLoginService.getKakaoUserIdFromAccessToken(accessToken);

        return ResponseEntity.ok(Map.of("kakao_user_id", kakaoUserId));
    }

}

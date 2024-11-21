package com.goormthon3.team49.domain.user.presentation;

import com.goormthon3.team49.domain.user.application.UserLoginService;
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

        Long userId = userInfo.getId(); //고유 사용자 ID
        String nickName = userInfo.getKakaoAccount().getProfile().getNickName();
        String profileImageUrl = userInfo.getKakaoAccount().getProfile().getProfileImageUrl();

        return ResponseEntity.ok(Map.of(
                "user_id", userId,
                "nickname", nickName,
                "profile_image_url", profileImageUrl
        ));
    }


}

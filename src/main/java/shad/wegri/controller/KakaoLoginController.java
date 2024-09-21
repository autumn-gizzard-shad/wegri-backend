package shad.wegri.controller;

import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shad.wegri.dto.LoginResponse;
import shad.wegri.service.KakaoLoginService;
import shad.wegri.util.KakaoProperties;

@RestController
@RequestMapping("/api/members/kakao")
@RequiredArgsConstructor
public class KakaoLoginController {

    private final KakaoProperties kakaoProperties;
    private final KakaoLoginService kakaoLoginService;

    private static final String KAKAO_OAUTH_CODE_URL = "https://kauth.kakao.com/oauth/authorize";

    @GetMapping("/login")
    public ResponseEntity<Object> kakaoLogin(Model model) {
        String url = KAKAO_OAUTH_CODE_URL
            + "?client_id=" + kakaoProperties.clientId()
            + "&redirect_uri=" + kakaoProperties.redirectUri()
            + "&response_type=code";
        return ResponseEntity.status(HttpStatus.PERMANENT_REDIRECT)
            .location(URI.create(url))
            .build();
    }

    @GetMapping("/oauth")
    public ResponseEntity<LoginResponse> kakaoCallback(@RequestParam("code") String code) {
        return ResponseEntity.ok().body(kakaoLoginService.login(code));
    }
}

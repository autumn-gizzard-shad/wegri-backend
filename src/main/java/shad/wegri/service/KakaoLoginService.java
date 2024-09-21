package shad.wegri.service;

import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import shad.wegri.domain.Member;
import shad.wegri.dto.KakaoProfileDto;
import shad.wegri.dto.KakaoTokenDto;
import shad.wegri.dto.LoginResponse;
import shad.wegri.exception.InvalidKakaoTokenException;
import shad.wegri.repository.KakaoTokenRepository;
import shad.wegri.repository.MemberRepository;
import shad.wegri.util.KakaoProperties;
import shad.wegri.util.jwt.JwtProvider;

@Service
public class KakaoLoginService {

    private final RestTemplate restTemplate;
    private final KakaoProperties kakaoProperties;
    private final JwtProvider jwtProvider;
    private final KakaoTokenRepository kakaoTokenRepository;
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private static final String KAKAO_OAUTH_TOKEN_URL = "https://kauth.kakao.com/oauth/token";
    private static final String KAKAO_PROFILE_URL = "https://kapi.kakao.com/v2/user/me";

    @Autowired
    public KakaoLoginService(RestTemplateBuilder restTemplateBuilder, KakaoProperties kakaoProperties,
        JwtProvider jwtProvider, KakaoTokenRepository kakaoTokenRepository,
        MemberRepository memberRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.restTemplate = restTemplateBuilder.build();
        this.kakaoProperties = kakaoProperties;
        this.jwtProvider = jwtProvider;
        this.kakaoTokenRepository = kakaoTokenRepository;
        this.memberRepository = memberRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public LoginResponse login(String code) {
        var kakaoTokenDto = getKakaoToken(code);
        var kakaoProfileDto = getKakaoProfile(kakaoTokenDto.access_token());

        String email = kakaoProfileDto.kakao_account().email();
        String id = kakaoProfileDto.id();
        kakaoTokenRepository.save(kakaoTokenDto.toEntity(email));
        memberRepository.save(
            Member.builder()
                .id(email)
                .password(bCryptPasswordEncoder.encode(id))
                .build());
        return new LoginResponse(jwtProvider.createToken(email));
    }

    private KakaoTokenDto getKakaoToken(String code) {
        var client = RestClient.builder(restTemplate).build();
        var body = new LinkedMultiValueMap<String, String>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", kakaoProperties.clientId());
        body.add("redirect_uri", kakaoProperties.redirectUri());
        body.add("code", code);
        try {
            return client.post()
                .uri(URI.create(KAKAO_OAUTH_TOKEN_URL))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(body)
                .retrieve()
                .body(KakaoTokenDto.class);
        } catch (HttpClientErrorException e) {
            throw new InvalidKakaoTokenException(e.getStatusCode(), e.getMessage());
        }
    }

    private KakaoProfileDto getKakaoProfile(String kakaoAccessToken) {
        var client = RestClient.builder(restTemplate).build();
        try {
            return client.get()
                .uri(URI.create(KAKAO_PROFILE_URL))
                .header("Authorization", "Bearer " + kakaoAccessToken)
                .retrieve()
                .body(KakaoProfileDto.class);
        } catch (HttpClientErrorException e) {
            throw new InvalidKakaoTokenException(e.getStatusCode(), e.getMessage());
        }
    }
}

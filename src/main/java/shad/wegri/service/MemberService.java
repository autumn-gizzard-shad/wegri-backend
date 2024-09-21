package shad.wegri.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import shad.wegri.dto.LoginRequest;
import shad.wegri.dto.LoginResponse;
import shad.wegri.exception.NoSuchMemberException;
import shad.wegri.jwt.JwtProvider;
import shad.wegri.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtProvider jwtProvider;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public LoginResponse login(LoginRequest loginRequest) {
        // 1. post body로 authenticationToken 객체 생성
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.member_id(), loginRequest.password());

        try {
            // 2. authenticationToken 객체로 검증, CustomUserDetailService의 loadUserByUsername 메서드 실행
            Authentication authentication = authenticationManagerBuilder
                .getObject()
                .authenticate(authenticationToken);

            // 3. authentication 객체로 JWT 생성
            return new LoginResponse(jwtProvider.createToken(authentication));
        } catch (Exception e) {
            throw new NoSuchMemberException();
        }
    }
}

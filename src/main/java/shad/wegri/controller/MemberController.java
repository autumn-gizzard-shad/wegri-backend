package shad.wegri.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shad.wegri.argumentresolver.LoginMemberId;
import shad.wegri.dto.LoginRequest;
import shad.wegri.dto.LoginResponse;
import shad.wegri.dto.MemberInfoResponse;
import shad.wegri.dto.RegisterRequest;
import shad.wegri.service.MemberService;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok().body(memberService.login(loginRequest));
    }

    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody RegisterRequest registerRequest) {
        memberService.register(registerRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<MemberInfoResponse> getMemberInfo(@LoginMemberId String memberId) {
        return ResponseEntity.ok().body(memberService.getMemberInfo(memberId));
    }
}

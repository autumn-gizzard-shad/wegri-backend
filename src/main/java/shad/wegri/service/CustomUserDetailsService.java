package shad.wegri.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import shad.wegri.domain.Member;
import shad.wegri.exception.NoSuchMemberException;
import shad.wegri.repository.MemberRepository;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository.findById(username)
            .map(this::createUserDetails)
            .orElseThrow(NoSuchMemberException::new);
    }

    private UserDetails createUserDetails(Member member) {
        return User.builder()
            .username(member.getUsername())
            .password(member.getPassword())
            .build();
    }
}

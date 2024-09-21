package shad.wegri.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import shad.wegri.exception.InvalidTokenException;

@Component
public class JwtProvider {

    private final SecretKey secretKey;
    private final long accessTokenExpirationTime;

    public JwtProvider(@Value("${jwt.secret-key}") String secretKey, @Value("${jwt.access-token-expiration-time}") long accessTokenExpirationTime) {
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey));
        this.accessTokenExpirationTime = accessTokenExpirationTime;
    }

    public String createToken(Authentication authentication) {
        Date now = new Date();
        return Jwts.builder()
            .claim("id", authentication.getName())
            .expiration(new Date(now.getTime() + accessTokenExpirationTime))
            .signWith(secretKey)
            .compact();
    }

    public Authentication getAuthentication(String token) {
        String id = parseToken(token);
        Collection<? extends GrantedAuthority> authorities = new ArrayList<>();
        UserDetails userDetails = new User(id, "", authorities);
        return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private String parseToken(String token) {
        try {
            return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("id")
                .toString();
        } catch (Exception e) {
            throw new InvalidTokenException();
        }
    }
}

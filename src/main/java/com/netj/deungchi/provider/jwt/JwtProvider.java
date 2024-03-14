package com.netj.deungchi.provider.jwt;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;


import jakarta.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Date;

@Component
public class JwtProvider {
    private final Key key;
    public JwtProvider(@Value("${jwt.secret}") String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }
    private final Long ACCESS_TOKEN_EXPIRED_TIME = 1000L * 60 * 60 * 60 * 60 * 60;
    private final Long REFREST_TOKEN_EXPIRED_TIME = 1000L * 60 * 60 * 24 * 14;

    public String getAccessToken(Long id){
        Date now = new Date();
        return Jwts.builder().setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .claim("userSeq", id)
                .setIssuer("beTravelic")
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + ACCESS_TOKEN_EXPIRED_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String getRefreshToken(){
        Date now = new Date();
        return Jwts.builder().setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer("beTravelic")
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + REFREST_TOKEN_EXPIRED_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String getIdFromAccessToken(String accessToken) throws Exception{
        String id = (String) Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody().get("id");
        return  id;
    }

    public Long getUserSeqFromAccessToken(String accessToken) throws Exception{
        System.out.println(Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody().get("id"));
        System.out.println(accessToken);
        Long userSeq = Long.parseLong(
                String.valueOf(Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody().get("id"))
        );
        System.out.println("bb");
        System.out.println(userSeq);

        return  userSeq;
    }

    public boolean isValidToken(String token){
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public Long getUserSeqFromRequest(HttpServletRequest request) throws Exception {
        final String requestTokenHeader = request.getHeader("Authorization");
        String accessToken = requestTokenHeader.substring("Bearer ".length());
        System.out.println("aaa");
        System.out.println(accessToken);
        return getUserSeqFromAccessToken(accessToken);
    }

}

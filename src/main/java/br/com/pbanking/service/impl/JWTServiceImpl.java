package br.com.pbanking.service.impl;

import br.com.pbanking.controller.login.requests.UserRequest;
import br.com.pbanking.service.JWTService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTServiceImpl implements JWTService{

    @Value("${security.jwt.tempo_expiracao}")
    private String expiration;

    @Value("${security.jwt.chave_assinatura}")
    private String chaveAssinatura;

    public String generateJWTToken(UserRequest user, String clientIP) {
        long expString = Long.valueOf(this.expiration);
        LocalDateTime dateHourExpiration = LocalDateTime.now().plusMinutes(expString);
        Instant instant = dateHourExpiration.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);
        Map<String, Object> claims = new HashMap<>();
        claims.put("ip", clientIP);

        return Jwts
                .builder()
                .setSubject(user.getName())
                .setExpiration(date)
                .addClaims(claims)
                .signWith(SignatureAlgorithm.HS512, this.chaveAssinatura)
                .compact();
    }

    public Claims obterClaims(String token) {
        return Jwts
                .parser()
                .setSigningKey(chaveAssinatura)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenValid(String token, String clientIP) {
        try {
            Claims claims = obterClaims(token);

            LocalDateTime dateExpToken = claims.getExpiration()
                    .toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
            return !LocalDateTime.now().isAfter(dateExpToken) && this.ipValid(token, clientIP);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean ipValid(String token, String clientIP) {
        try {
            Claims claims = obterClaims(token);
            final String ipToken = claims.get("ip").toString();
            return ipToken.equals(clientIP);
        } catch (Exception e) {
            return false;
        }
    }

    public void invalidarToken(String token) {
//        try {
//            Claims claims = obterClaims(token);
//            LocalDateTime dateExpToken = claims.getExpiration()
//                    .toInstant()
//                    .atZone(ZoneId.systemDefault())
//                    .toLocalDateTime();
//            return !LocalDateTime.now().isAfter(dateExpToken);
//        } catch (Exception e) {
//            return false;
//        }
    }

    public String obterNomeUsuario(String token) {
        return obterClaims(token).getSubject();
    }

//    public static void main(String[] args) {
//        ConfigurableApplicationContext context = SpringApplication.run(PBankingApplication.class);
//        JWTServiceImpl service = context.getBean(JWTServiceImpl.class);
//        User user = new User();
//        user.setName("user");
//        user.setPassword("pass");
//        String token = service.generateJWTToken(user);
//        System.out.println(token);
//
//        System.out.println(service.isTokenValid(token));
//
//        System.out.println(service.obterClaims(token));
//
//        System.out.println(service.obterNomeUsuario(token));
//    }
}

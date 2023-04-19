package br.com.pbanking.service;

import br.com.pbanking.controller.login.requests.UserRequest;
import io.jsonwebtoken.Claims;

public interface JWTService {
    public String generateJWTToken(UserRequest user, String clientIP);
    public boolean isTokenValid(String token, String clientIP);
    public Claims obterClaims(String token);
    public String obterNomeUsuario(String token);
}

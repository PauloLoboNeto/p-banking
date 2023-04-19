package br.com.pbanking.service;

import br.com.pbanking.controller.login.requests.UserRequest;
import br.com.pbanking.exception.SenhaInvalidaException;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.servlet.http.HttpServletRequest;

public interface CustomUserService extends UserDetailsService {
    void autenticar(UserRequest user) throws SenhaInvalidaException;
    String getClientIp(HttpServletRequest request);
}

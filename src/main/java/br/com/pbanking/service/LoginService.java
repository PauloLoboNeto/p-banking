package br.com.pbanking.service;

import br.com.pbanking.controller.login.requests.UserRequest;
import br.com.pbanking.controller.login.response.DoLoginResponse;
import br.com.pbanking.exception.SenhaInvalidaException;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.servlet.http.HttpServletRequest;

public interface LoginService {
    public DoLoginResponse doLogin(UserRequest user, HttpServletRequest request);
    public DoLoginResponse doLogout(String token);
}

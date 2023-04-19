package br.com.pbanking.service.impl;

import br.com.pbanking.controller.login.requests.UserRequest;
import br.com.pbanking.controller.login.response.DoLoginResponse;
import br.com.pbanking.service.CustomUserService;
import br.com.pbanking.service.JWTService;
import br.com.pbanking.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private CustomUserService customUserService;
    @Autowired
    private JWTService jwtService;
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Override
//    @CircuitBreaker(name = "doLoginCB", fallbackMethod = "fallBackDoLoginCB")
    public DoLoginResponse doLogin(UserRequest user, HttpServletRequest request) {
        DoLoginResponse doLoginResponse = new DoLoginResponse();
        this.customUserService.autenticar(user);
        final String clientIP = this.customUserService.getClientIp(request);
        final String token = jwtService.generateJWTToken(user, clientIP);
        doLoginResponse.setToken(token);
        doLoginResponse.setName(user.getName());
        return doLoginResponse;
    }


    @Override
    public DoLoginResponse doLogout(String token) {
        redisTemplate.opsForValue().set(this.jwtService.obterNomeUsuario(token),token, 20);
        return null;
    }

//    private DoLoginResponse fallBackDoLoginCB(UserRequest user, Throwable e) {
//        DoLoginResponse doLoginResponse = new DoLoginResponse();
//        this.customUserService.autenticar(user);
//        String token = jwtService.generateJWTToken(user);
//        doLoginResponse.setToken(token);
//        doLoginResponse.setName(user.getName());
//        return doLoginResponse;
//    }
}

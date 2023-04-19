package br.com.pbanking.service.impl;

import br.com.pbanking.exception.SenhaInvalidaException;
import br.com.pbanking.controller.login.requests.UserRequest;
import br.com.pbanking.service.CustomUserService;
import br.com.pbanking.utils.Encoder;
import br.com.pbanking.utils.Validator;
import functions.Function;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class CustomUserServiceImpl implements CustomUserService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(!username.equals("user")){
            throw  new UsernameNotFoundException("Usuário não encontrado!");
        }

        return  User
                .builder()
                .passwordEncoder(new Encoder().encoder()::encode)
                .username("user")
                .password("password")
                .roles("USER")
                .build();
    }

    public void autenticar(UserRequest user) throws SenhaInvalidaException{
        UserDetails userDetails = loadUserByUsername(user.getName());
        Boolean passwordValid = new Validator().isPasswordValid.apply(user.getPassword(), userDetails.getPassword());
        if(passwordValid) return;
        throw new SenhaInvalidaException();
    }

    @Override
    public String getClientIp(HttpServletRequest request) {
        String remoteAddr = "";
        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }
        return remoteAddr;
    }
}

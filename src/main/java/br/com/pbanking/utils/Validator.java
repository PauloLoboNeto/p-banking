package br.com.pbanking.utils;

import br.com.pbanking.controller.login.requests.UserRequest;
import functions.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Validator {
    public Function<String, String, Boolean> isPasswordValid = (passEntry, passDataBase) -> new Encoder().encoder().matches(passEntry, passDataBase);
}

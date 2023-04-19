package br.com.pbanking.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Encoder {
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}

package br.com.pbanking.controller.login.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class DoLoginResponse implements Serializable {

    private String name;
    private String token;
}

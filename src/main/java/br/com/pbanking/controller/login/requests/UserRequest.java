package br.com.pbanking.controller.login.requests;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class  UserRequest implements Serializable {

    private String name;
    private String password;
}

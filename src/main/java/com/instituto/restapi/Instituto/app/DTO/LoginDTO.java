package com.instituto.restapi.Instituto.app.DTO;

import lombok.Data;

@Data
public class LoginDTO {

    private String usernameOrEmail;
    private String password;
}

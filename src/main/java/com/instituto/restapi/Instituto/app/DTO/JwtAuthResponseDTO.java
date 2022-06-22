package com.instituto.restapi.Instituto.app.DTO;

import lombok.Data;

@Data
public class JwtAuthResponseDTO {

    private String accessToken;
    private String tokenType;

    public JwtAuthResponseDTO(String accessToken) {
        this.accessToken = accessToken;
        tokenType = "Bearer";
    }


}

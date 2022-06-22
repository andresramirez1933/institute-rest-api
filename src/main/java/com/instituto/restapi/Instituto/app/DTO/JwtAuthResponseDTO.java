package com.instituto.restapi.Instituto.app.DTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class JwtAuthResponseDTO {

    @ApiModelProperty(value = "Access token")
    private String accessToken;
    @ApiModelProperty(value = "Token type")
    private String tokenType;

    public JwtAuthResponseDTO(String accessToken) {
        this.accessToken = accessToken;
        tokenType = "Bearer";
    }


}

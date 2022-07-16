package com.instituto.restapi.Instituto.app.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgeRangeRequest {

    private Integer startAge;
    private Integer endAge;
}

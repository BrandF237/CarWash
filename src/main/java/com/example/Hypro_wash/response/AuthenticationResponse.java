package com.example.Hypro_wash.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter @Setter
public class AuthenticationResponse {
    private final String jwt;

}

package com.example.Hypro_wash.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter @Setter
public class UserRegistrationRequest {
     private String username;
    private String password;
}

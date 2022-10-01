package com.poc.FarmaLoginService.dtos.network;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AuthSuccessulResponseDTO {

    private String token;
    private String type;
    private String clientIdToken;

    public AuthSuccessulResponseDTO(String token, String type, String clientIdTokenx) {
       this.token = token;
       this.type=type;
       this.clientIdToken = clientIdTokenx;

   }
   public AuthSuccessulResponseDTO(){}
}

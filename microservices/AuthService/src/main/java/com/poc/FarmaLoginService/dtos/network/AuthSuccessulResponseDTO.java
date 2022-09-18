package com.poc.FarmaLoginService.dtos.network;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AuthSuccessulResponseDTO {

    private String token;
    private String type;

    public AuthSuccessulResponseDTO(String token, String type) {
       this.token = token;
       this.type=type;
   }
   public AuthSuccessulResponseDTO(){}
}

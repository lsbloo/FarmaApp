package com.poc.FarmaLoginService.controller;

import com.poc.FarmaLoginService.base.model.MessageClientResponseDTO;
import com.poc.FarmaLoginService.dtos.network.MessageResponseDTO;
import com.poc.FarmaLoginService.services.AuthenticatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api/user")
public class UserInfoController {


    @Autowired
    private AuthenticatorService authenticatorService;


    @PostMapping("/name")
    public ResponseEntity<?> obtainNameUser() {
        String nameUser = this.authenticatorService.getNameOfUser();
        if (nameUser != null) {
            return ResponseEntity.status(HttpServletResponse.SC_ACCEPTED).body(
                    new MessageClientResponseDTO(
                            "OK",
                            "Recovery name accepted",
                            HttpServletResponse.SC_ACCEPTED,
                            nameUser
                    ));
        } else {
            return ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST).body(
                    new MessageClientResponseDTO(
                            "Failure",
                            "Failure to obtain name of user",
                            HttpServletResponse.SC_BAD_REQUEST,
                            null
                    ));
        }
    }
}

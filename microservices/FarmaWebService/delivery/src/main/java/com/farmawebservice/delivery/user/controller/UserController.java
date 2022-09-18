package com.farmawebservice.delivery.user.controller;


import com.farmawebservice.delivery.user.model.UserDTO;
import com.farmawebservice.delivery.user.service.UserService;
import com.farmawebservice.delivery.util.messages.dtos.MessageResponseDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/user")
public class UserController {


    @Autowired
    private UserService userService;


    @PostMapping
    @ApiOperation(value = "create only reference of user", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created"),
            @ApiResponse(code = 404, message = "User Dont Created - Error Generic"),
            @ApiResponse(code = 500, message = "Error Internal")
    })
    public ResponseEntity<MessageResponseDTO> saveUser(@RequestBody UserDTO userDTO) {
        Long re = this.userService.saveUser(userDTO);
        if (re != -1) {
            return ResponseEntity.status(HttpServletResponse.SC_ACCEPTED).body(MessageResponseDTO.build(
                    "User Created", "Successful", HttpServletResponse.SC_ACCEPTED,re
            ));
        } else {
            return ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST).body(MessageResponseDTO.build(
                    "User Dont Created", "Error Generic", HttpServletResponse.SC_BAD_REQUEST,re
            ));
        }
    }

}

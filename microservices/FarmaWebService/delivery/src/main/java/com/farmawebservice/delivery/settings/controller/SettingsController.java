package com.farmawebservice.delivery.settings.controller;

import com.farmawebservice.delivery.settings.model.SettingsLabelDTO;
import com.farmawebservice.delivery.settings.service.SettingsService;
import com.farmawebservice.delivery.util.messages.dtos.MessageResponseDTO;
import com.google.gson.Gson;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/settings")
public class SettingsController {


    @Autowired
    private SettingsService settingsService;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "get settings by user", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully recovery settings screen data"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500,message = "Error Internal")
    })
    public ResponseEntity<MessageResponseDTO> getSettingsByUser(@RequestHeader("accessToken") String accessToken) {

        SettingsLabelDTO settingsLabelDTO = this.settingsService.getSettingsDataByUser(accessToken);

        if(settingsLabelDTO != null) {
            return ResponseEntity.status(HttpServletResponse.SC_ACCEPTED).body(MessageResponseDTO.build(
                    "OK",
                    "settings data has found",
                    HttpServletResponse.SC_ACCEPTED,
                    new Gson().toJson(settingsLabelDTO)
            ));
        } else {
            return ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST).body(MessageResponseDTO.build(
                    "Failure",
                    "settings data dont has found",
                    HttpServletResponse.SC_BAD_REQUEST,
                    null
            ));
        }
    }
}

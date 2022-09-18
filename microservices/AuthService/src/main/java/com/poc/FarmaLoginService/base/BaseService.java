package com.poc.FarmaLoginService.base;

import com.google.gson.Gson;
import com.poc.FarmaLoginService.base.model.MessageClientResponseDTO;
import com.poc.FarmaLoginService.connector.client.WebClientBuilder;
import org.springframework.security.core.context.SecurityContextHolder;

import static com.poc.FarmaLoginService.base.ConstantsApplication.BASE_URL_FARMA_SERVICE;

public class BaseService {
    public WebClientBuilder getFarmClient() {
        return WebClientBuilder.getInstance(BASE_URL_FARMA_SERVICE);
    }

    public  MessageClientResponseDTO convertClientResponseToDTO(String data) {
        Gson gson = new Gson();
        return gson.fromJson(data, MessageClientResponseDTO.class);
    }

    public String getUserNameAuthenticate() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
           return authentication.getName();
        } else return null;
    }
}

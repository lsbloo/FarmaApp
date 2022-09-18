package com.farmawebservice.delivery.base.service;

import com.farmawebservice.delivery.base.connector.WebClientBuilder;
import com.farmawebservice.delivery.base.model.MessageClientResponseDTO;
import com.farmawebservice.delivery.user.model.User;
import com.farmawebservice.delivery.user.repository.UserRepository;
import com.google.gson.Gson;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static com.farmawebservice.delivery.base.connector.Foo.BASE_URL_FARM_AUTH_API;

public class BaseService {

    private String clientId;

    public WebClientBuilder getAuthClient() {
        return WebClientBuilder.getInstance(BASE_URL_FARM_AUTH_API);
    }

    public MessageClientResponseDTO convertClientResponseToDTO(String data) {
        Gson gson = new Gson();
        return gson.fromJson(data, MessageClientResponseDTO.class);
    }

    public Long getClientId(String clientId) {
        byte[] encoded = Base64.getDecoder().
                decode(clientId.getBytes(StandardCharsets.UTF_8));
        String dataDecoded = new String(encoded);
        return Long.parseLong(dataDecoded.split("=")[1]);
    }

    public User authenticateUserClient(UserRepository userRepository) {
        userRepository.findByUserAuthId(getClientId(clientId));
        return userRepository.findByUserAuthId(getClientId(clientId));
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

}

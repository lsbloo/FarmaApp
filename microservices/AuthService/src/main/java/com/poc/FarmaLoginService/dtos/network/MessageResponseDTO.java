package com.poc.FarmaLoginService.dtos.network;

import com.poc.FarmaLoginService.base.model.MessageClientResponseDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MessageResponseDTO {
    private String message;
    private String description;
    private Boolean isError;
    private MessageClientResponseDTO messageClientResponseDTO;


    public MessageResponseDTO(String message){
        this.message = message;
    }

    public MessageResponseDTO(String message, Boolean isError){
        this.message = message;
        this.isError = isError;
    }

    public MessageResponseDTO(String message, String description) {
        this.message = message;
        this.description = description;
    }

    public MessageResponseDTO(String message, boolean isError,String description, MessageClientResponseDTO dto) {
        this.message = message;
        this.description = description;
        this.messageClientResponseDTO = dto;
        this.isError = isError;
    }

}

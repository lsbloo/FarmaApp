package com.farmawebservice.delivery.base.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MessageClientResponseDTO {
    private String title;
    private String description;
    private Integer httpStatusCode;
    private String responseDTO;

    public MessageClientResponseDTO(String title, String description, Integer httpStatusCode, String responseDTO) {
        setTitle(title);
        setDescription(description);
        setHttpStatusCode(httpStatusCode);
        setResponseDTO(responseDTO);
    }
}
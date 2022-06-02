package com.farmawebservice.delivery.util.messages.dtos;

import com.farmawebservice.delivery.products.dto.ProductDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class MessageResponseDTO {
    private String title;
    private String description;
    private String httpResponse;

    @Override
    public String toString() {
        return "MessageResponseDTO{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", httpResponse='" + httpResponse + '\'' +
                '}';
    }


    public static MessageResponseDTO setupFktoryMessageOK(ProductDTO productDTO) {
        MessageResponseDTO messageResponseDTO = new MessageResponseDTO();
        messageResponseDTO.setHttpResponse("202");
        messageResponseDTO.setTitle("Produto saved");
        messageResponseDTO.setDescription("Product with name: " + productDTO.getName() + "Has Saved");
        return messageResponseDTO;
    }

    public static MessageResponseDTO setupFktoryMessageBadRequest(ProductDTO productDTO) {
        MessageResponseDTO messageResponseDTO = new MessageResponseDTO();
        messageResponseDTO.setHttpResponse("400");
        messageResponseDTO.setTitle("Product dont saved");
        messageResponseDTO.setDescription("Product with name: " + productDTO.getName() + "Dont Saved");
        return messageResponseDTO;
    }
}

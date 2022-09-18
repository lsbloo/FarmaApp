package com.farmawebservice.delivery.util.messages.dtos;

import com.farmawebservice.delivery.products.dto.ProductDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.servlet.http.HttpServletResponse;

@NoArgsConstructor
@Getter
@Setter
public class MessageResponseDTO {
    private String title;
    private String description;
    private Integer httpStatusCode;
    private Object responseDTO;

    public MessageResponseDTO(String title, String description, Integer statusCode, Object responseDTO) {
        setTitle(title);
        setDescription(description);
        setHttpStatusCode(statusCode);
        setResponseDTO(responseDTO);
    }

    public MessageResponseDTO(String title, String description, Integer statusCode) {
        setTitle(title);
        setDescription(description);
        setHttpStatusCode(statusCode);
    }

    @Override
    public String toString() {
        return "MessageResponseDTO{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", httpResponse='" + httpStatusCode + '\'' +
                '}';
    }


    public static MessageResponseDTO setupFktoryMessageOK(ProductDTO productDTO) {
        MessageResponseDTO messageResponseDTO = new MessageResponseDTO();
        messageResponseDTO.setHttpStatusCode(HttpServletResponse.SC_ACCEPTED);
        messageResponseDTO.setTitle("Produto saved");
        messageResponseDTO.setDescription("Product with name: " + productDTO.getName() + "Has Saved");
        return messageResponseDTO;
    }

    public static MessageResponseDTO setupFktoryMessageBadRequest(ProductDTO productDTO) {
        MessageResponseDTO messageResponseDTO = new MessageResponseDTO();
        messageResponseDTO.setHttpStatusCode(HttpServletResponse.SC_BAD_REQUEST);
        messageResponseDTO.setTitle("Product dont saved");
        messageResponseDTO.setDescription("Product with name: " + productDTO.getName() + "Dont Saved");
        return messageResponseDTO;
    }

    public static MessageResponseDTO build(String title, String description, Integer statusCode,Object responseDTO) {
        return new MessageResponseDTO(title,description,statusCode,responseDTO);
    }
    public static MessageResponseDTO build(String title, String description, Integer statusCode) {
        return new MessageResponseDTO(title,description,statusCode);
    }
}

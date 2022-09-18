package com.farmawebservice.delivery.base.validator.resource;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ResourceMessage {
    public String messageValidator;
    public String descriptionValidator;
    public Boolean isError;
    public Object responseDTO;


    public static String GENERIC_ERROR = "A generic error has occurred try agay";
}

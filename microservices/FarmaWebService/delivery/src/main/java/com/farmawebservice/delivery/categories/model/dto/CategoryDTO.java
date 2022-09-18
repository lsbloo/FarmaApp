package com.farmawebservice.delivery.categories.model.dto;

import com.farmawebservice.delivery.categories.model.CategoryTypes;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDTO {
    private String name;
    private String urlImage;
    private CategoryTypes type;
}

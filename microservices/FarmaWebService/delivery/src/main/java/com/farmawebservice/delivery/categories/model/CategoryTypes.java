package com.farmawebservice.delivery.categories.model;

public enum CategoryTypes {

    MEDICAMENTO("MEDICAMENTO"),
    SAUDE("SAUDE"),
    BELEZA("BELEZA");

    private String valueType;

    CategoryTypes(String value) {
        valueType = value;
    }
}

package com.farmawebservice.delivery.base.validator.core;

public interface Validator<T> {
     void validate(Result result,  T object);
}

interface ValidatorWithUser<T,R> {
     void validateWithUser(Result result, T object, R objectR);
}
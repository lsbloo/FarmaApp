package com.farmawebservice.delivery.base.validator.core;

import java.util.LinkedList;

public class ValidatorBuilder<T> {

    private LinkedList<Validator<T>> validators;


    public ValidatorBuilder() {
        this.validators = new LinkedList<>();
    }


    public ValidatorBuilder<T> apply(Validator<T> validator) {
        validators.add(validator);
        return this;
    }

    public Result validate(T object) {
        Result result = new Result();

        for (Validator<T> validator : validators) {
            validator.validate(result, object);
        }
        return result;
    }
}

class ValidatorBuilderWithUser<T, R> {

    private LinkedList<ValidatorWithUser<T, R>> validators;


    public ValidatorBuilderWithUser() {
        this.validators = new LinkedList<>();
    }


    public ValidatorBuilderWithUser<T, R> apply(ValidatorWithUser<T, R> validator) {
        validators.add(validator);
        return this;
    }

    public Result validate(T object, R objectR) {
        Result result = new Result();

        for (ValidatorWithUser<T, R> validator : validators) {
            validator.validateWithUser(result, object, objectR);
        }
        return result;
    }
}

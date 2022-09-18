package com.farmawebservice.delivery.base.validator.core;

import com.farmawebservice.delivery.base.validator.resource.ResourceMessage;

import java.util.LinkedHashSet;
import java.util.Set;

public class Result {

    private Set<String> erros;

    private Set<String> warnings;

    private Set<String> sucess;

    private ResourceMessage resourceMessage;


    public static Result ok(String sucess) {
        Result results = new Result();
        results.sucess(sucess);
        return results;
    }

    public Result() {
        this.erros = new LinkedHashSet<>();
        this.warnings = new LinkedHashSet<>();
        this.sucess = new LinkedHashSet<>();
        this.resourceMessage = new ResourceMessage();
    }

    public Result sucess(String sucess) {
        this.sucess.add(sucess);
        return this;
    }

    public ResourceMessage setResourceMessage(String message, String description, Boolean isError) {
        this.resourceMessage.setMessageValidator(message);
        this.resourceMessage.setDescriptionValidator(description);
        this.resourceMessage.setIsError(isError);
        return this.resourceMessage;
    }

    public ResourceMessage setResourceMessage(String message, Boolean isError) {
        this.resourceMessage.setMessageValidator(message);
        this.resourceMessage.setIsError(isError);
        return this.resourceMessage;
    }

    public ResourceMessage setResourceMessage(String message, Boolean isError, Object response) {
        this.resourceMessage.setMessageValidator(message);
        this.resourceMessage.setIsError(isError);
        this.resourceMessage.setResponseDTO(response);
        return this.resourceMessage;
    }

    public ResourceMessage getResourceMessage() {
        return resourceMessage;
    }


    public Result warning(String warning) {
        this.warnings.add(warning);

        return this;
    }


    public Result error(String error) {
        this.erros.add(error);
        return this;
    }

    /**
     * Return if there is no errors.
     *
     * @return true if it has no errors.
     */
    public boolean ok() {
        return this.erros == null || this.erros.isEmpty();
    }

    /**
     * Return if there is at least one error
     *
     * @return true if it has errors.
     */
    public boolean error() {
        return this.erros != null && !this.erros.isEmpty();
    }

    public Set<String> getErrors() {
        return erros;
    }

    public Set<String> getWarnings() {
        return warnings;
    }

    public Set<String> getSuccess() {
        return sucess;
    }
}

package com.instituto.restapi.Instituto.app.exception;

public class ResourceNotFound extends RuntimeException {

    public ResourceNotFound(String object, Long id) {
        super( object +  " not found with id: " + id);
    }
}

package com.revature.foodMartApi.exceptions;

@SuppressWarnings("serial")
public class ResourcePersistenceException extends RuntimeException {

    public ResourcePersistenceException(String message) {
        super(message);
    }

}

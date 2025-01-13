package ru.clevertec.exception;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException {
    private UserNotFoundException(String message) {
        super(message);
    }

    public static UserNotFoundException byId(UUID id) {
        return new UserNotFoundException(String.format("User with keycloak-id %s not found", id));
    }
}

package ru.clevertec.util;

import ru.clevertec.domain.User;
import ru.clevertec.port.input.command.UserRegisterCommand;
import ru.clevertec.port.input.command.UserUseCaseResult;
import ru.clevertec.port.output.port.UserPortResult;
import ru.clevertec.port.output.port.UserRegisterPortCommand;

import java.util.UUID;

public class TestData {

    public static User generateUser() {
        return User.builder()
                .id(UUID.randomUUID())
                .keycloakUserId(UUID.randomUUID())
                .username("Nekitos4")
                .build();
    }

    public static UserUseCaseResult generateUserUseCaseResult() {
        return UserUseCaseResult.builder()
                .id(UUID.randomUUID())
                .keycloakUserId(UUID.randomUUID())
                .username("Nekitos4")
                .build();
    }

    public static UserRegisterCommand generateUserRegisterCommand() {
        return UserRegisterCommand.builder()
                .keycloakUserId(UUID.randomUUID())
                .username("Nekitos4")
                .build();
    }

    public static UserRegisterPortCommand generateUserRegisterPortCommand() {
        return UserRegisterPortCommand.builder()
                .keycloakUserId(UUID.randomUUID())
                .username("Nekitos4")
                .build();
    }

    public static UserPortResult generateUserPortResult() {
        return UserPortResult.builder()
                .id(UUID.randomUUID())
                .keycloakUserId(UUID.randomUUID())
                .username("Nekitos4")
                .build();
    }
}

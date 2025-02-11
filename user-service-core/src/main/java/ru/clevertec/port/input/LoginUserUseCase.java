package ru.clevertec.port.input;

import ru.clevertec.port.input.command.UserUseCaseResult;

import java.util.UUID;

public interface LoginUserUseCase {
    UserUseCaseResult readUserByKeycloakUserId(UUID keycloakUserId);
}

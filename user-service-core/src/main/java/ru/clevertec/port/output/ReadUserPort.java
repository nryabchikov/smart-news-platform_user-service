package ru.clevertec.port.output;

import ru.clevertec.port.output.port.UserPortResult;

import java.util.UUID;

public interface ReadUserPort {
    UserPortResult readUserByKeycloakUserId(UUID keycloakUserId);
}

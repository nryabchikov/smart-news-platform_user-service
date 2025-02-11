package ru.clevertec.port.input.command;

import lombok.Builder;

import java.util.UUID;

@Builder
public record UserRegisterCommand(UUID keycloakUserId, String username) {
}

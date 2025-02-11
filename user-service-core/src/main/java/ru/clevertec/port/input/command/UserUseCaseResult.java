package ru.clevertec.port.input.command;

import lombok.Builder;

import java.util.UUID;

@Builder
public record UserUseCaseResult(UUID id, UUID keycloakUserId, String username) {
}

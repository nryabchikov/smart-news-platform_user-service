package ru.clevertec.port.output.port;

import lombok.Builder;

import java.util.UUID;

@Builder
public record UserPortResult(UUID id, UUID keycloakUserId, String username) {
}

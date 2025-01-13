package ru.clevertec.port.output.port;

import lombok.Builder;

import java.util.UUID;

@Builder
public record UserRegisterPortCommand(UUID keycloakUserId, String username) {
}

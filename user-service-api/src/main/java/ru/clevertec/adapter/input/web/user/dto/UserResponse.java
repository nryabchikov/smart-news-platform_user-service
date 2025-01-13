package ru.clevertec.adapter.input.web.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.UUID;

@Builder
public record UserResponse(
        @NotNull(message = "Id should not be null.") UUID id,
        @NotNull(message = "KeycloakUserId should not be null.") UUID keycloakUserId,
        @NotBlank(message = "Username should not be blank.") String username
) {}

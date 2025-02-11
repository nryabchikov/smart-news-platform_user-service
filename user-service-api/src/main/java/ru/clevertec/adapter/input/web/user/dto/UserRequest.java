package ru.clevertec.adapter.input.web.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.util.UUID;

@Builder
public record UserRequest(
        @NotNull(message = "KeycloakUserId should not be null.") UUID keycloakUserId,
        @Size(min = 3, max = 50)
        @NotBlank(message = "Username should not be blank.") String username
) {}

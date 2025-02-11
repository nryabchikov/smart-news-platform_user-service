package ru.clevertec.util;

import ru.clevertec.adapter.input.web.user.dto.UserRequest;
import ru.clevertec.adapter.input.web.user.dto.UserResponse;
import ru.clevertec.adapter.output.persistence.jpa.entity.UserEntity;
import ru.clevertec.port.input.command.UserRegisterCommand;
import ru.clevertec.port.input.command.UserUseCaseResult;
import ru.clevertec.port.output.port.UserPortResult;
import ru.clevertec.port.output.port.UserRegisterPortCommand;

import java.util.UUID;

public class TestData {

    public static UserEntity generateUserEntity() {
        return UserEntity.builder()
                .id(UUID.fromString("d76603b5-47eb-4a34-ab48-1525a0ca2683"))
                .keycloakUserId(UUID.fromString("bc4e8f32-e59c-40a0-a037-213171ea7850"))
                .username("qzil4f5wkvrtc1pmfdjpp4hayo2f")
                .build();
    }

    public static UserPortResult generateUserPortResult() {
        return UserPortResult.builder()
                .id(UUID.fromString("d76603b5-47eb-4a34-ab48-1525a0ca2683"))
                .keycloakUserId(UUID.fromString("bc4e8f32-e59c-40a0-a037-213171ea7850"))
                .username("qzil4f5wkvrtc1pmfdjpp4hayo2f")
                .build();
    }

    public static UserRegisterCommand generateUserRegisterCommand() {
        return UserRegisterCommand.builder()
                .keycloakUserId(UUID.fromString("bc4e8f32-e59c-40a0-a037-213171ea7850"))
                .username("qzil4f5wkvrtc1pmfdjpp4hayo2f")
                .build();
    }

    public static UserRegisterPortCommand generateUserRegisterPortCommand() {
        return UserRegisterPortCommand.builder()
                .keycloakUserId(UUID.fromString("bc4e8f32-e59c-40a0-a037-213171ea7850"))
                .username("qzil4f5wkvrtc1pmfdjpp4hayo2f")
                .build();
    }

    public static UserResponse generateUserResponse() {
        return UserResponse.builder()
                .id(UUID.fromString("d76603b5-47eb-4a34-ab48-1525a0ca2683"))
                .keycloakUserId(UUID.fromString("bc4e8f32-e59c-40a0-a037-213171ea7850"))
                .username("qzil4f5wkvrtc1pmfdjpp4hayo2f")
                .build();
    }

    public static UserRequest generateUserRequest() {
        return UserRequest.builder()
                .keycloakUserId(UUID.fromString("bc4e8f32-e59c-40a0-a037-213171ea7850"))
                .username("qzil4f5wkvrtc1pmfdjpp4hayo2f")
                .build();
    }

    public static UserUseCaseResult generateUserUseCaseResult() {
        return UserUseCaseResult.builder()
                .id(UUID.fromString("d76603b5-47eb-4a34-ab48-1525a0ca2683"))
                .keycloakUserId(UUID.fromString("bc4e8f32-e59c-40a0-a037-213171ea7850"))
                .username("qzil4f5wkvrtc1pmfdjpp4hayo2f")
                .build();
    }

}

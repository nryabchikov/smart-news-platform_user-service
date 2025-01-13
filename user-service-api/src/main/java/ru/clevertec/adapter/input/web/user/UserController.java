package ru.clevertec.adapter.input.web.user;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.adapter.input.web.user.dto.UserRequest;
import ru.clevertec.adapter.input.web.user.dto.UserResponse;
import ru.clevertec.port.input.LoginUserUseCase;
import ru.clevertec.port.input.RegisterUserUseCase;

import java.util.UUID;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final LoginUserUseCase loginUserUseCase;
    private final RegisterUserUseCase registerUserUseCase;

    private final WebUserMapper userMapper;


    @GetMapping("/{keycloakId}")
    public ResponseEntity<UserResponse> readUserByKeycloakId(
            @PathVariable("keycloakId") @Valid @NotNull UUID keycloakId) {

        UserResponse userResponse = userMapper.toUserResponse(
                loginUserUseCase.readUserByKeycloakUserId(keycloakId)
        );

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(userResponse);
    }

    @PostMapping
    public ResponseEntity<UserResponse> createOrUpdateUser(@RequestBody UserRequest userRequest) {

        UserResponse userResponse = userMapper.toUserResponse(
                registerUserUseCase.createUser(userMapper.toUserRegisterCommand(userRequest))
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(userResponse);
    }
}


package ru.clevertec.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.clevertec.cache.Cache;
import ru.clevertec.domain.User;
import ru.clevertec.mapper.DomainUserMapper;
import ru.clevertec.port.input.RegisterUserUseCase;
import ru.clevertec.port.input.command.UserRegisterCommand;
import ru.clevertec.port.input.command.UserUseCaseResult;
import ru.clevertec.port.output.WriteUserPort;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegisterUserService implements RegisterUserUseCase {
    private final WriteUserPort userPort;
    private final DomainUserMapper userMapper;
    private final Cache<UUID, User> cache;

    @Override
    public UserUseCaseResult createUser(UserRegisterCommand userRegisterCommand) {
        log.info("RegisterUserService.createUser: Creating user. command: {}", userRegisterCommand);
        User user = userMapper.toUser(userRegisterCommand);
        user.setId(UUID.randomUUID());
        cache.put(user.getKeycloakUserId(), user);
        UserUseCaseResult userUseCaseResult = userMapper.toUserUseCaseResult(
                userPort.createOrUpdateUser(userMapper.toUserRegisterPortCommand(user))
        );
        log.info("RegisterUserService.createUser: User created successfully. Result: {}", userUseCaseResult);
        return userUseCaseResult;
    }
}

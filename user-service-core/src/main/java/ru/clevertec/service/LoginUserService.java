package ru.clevertec.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.clevertec.cache.Cache;
import ru.clevertec.domain.User;
import ru.clevertec.mapper.DomainUserMapper;
import ru.clevertec.port.input.LoginUserUseCase;
import ru.clevertec.port.input.command.UserUseCaseResult;
import ru.clevertec.port.output.ReadUserPort;
import ru.clevertec.port.output.port.UserPortResult;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginUserService implements LoginUserUseCase {
    private final ReadUserPort userPort;
    private final DomainUserMapper userMapper;
    private final Cache<UUID, User> cache;

    @Override
    public UserUseCaseResult readUserByKeycloakUserId(UUID keycloakUserId) {
        log.info("LoginUserService.readUserByKeycloakUserId: Reading user. keycloakUserId: {}", keycloakUserId);
        UserUseCaseResult userUseCaseResult = cache.get(keycloakUserId)
                .map(userMapper::toUserUseCaseResult)
                .orElseGet(() -> {
                    UserPortResult userPortResult = userPort.readUserByKeycloakUserId(keycloakUserId);
                    User user = userMapper.toUser(userPortResult);
                    cache.put(keycloakUserId, user);
                    return userMapper.toUserUseCaseResult(user);
                });
        log.info("LoginUserService.readUserByKeycloakUserId: User read successfully. Result: {}", userUseCaseResult);
        return userUseCaseResult;
    }
}

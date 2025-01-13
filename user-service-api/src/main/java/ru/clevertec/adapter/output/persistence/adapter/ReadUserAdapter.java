package ru.clevertec.adapter.output.persistence.adapter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.clevertec.adapter.output.persistence.PersistenceUserMapper;
import ru.clevertec.adapter.output.persistence.jpa.repository.UserRepository;
import ru.clevertec.exception.UserNotFoundException;
import ru.clevertec.port.output.ReadUserPort;
import ru.clevertec.port.output.port.UserPortResult;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReadUserAdapter implements ReadUserPort {

    private final UserRepository userRepository;
    private final PersistenceUserMapper userMapper;

    @Override
    public UserPortResult readUserByKeycloakUserId(UUID keycloakUserId) {
        log.info("ReadUserAdapter.readUserByKeycloakUserId: Reading user. keycloakUserId: {}", keycloakUserId);
        UserPortResult userPortResult = userRepository.findByKeycloakUserId(keycloakUserId)
                .map(userMapper::toUserPortResult)
                .orElseThrow(() -> {
                    log.error("ReadUserAdapter.readUserByKeycloakUserId: User not found. keycloakUserId: {}",
                            keycloakUserId);
                    return UserNotFoundException.byId(keycloakUserId);
                });
        log.debug("ReadUserAdapter.readUserByKeycloakUserId: User read successfully. Result: {}", userPortResult);
        return userPortResult;
    }
}

package ru.clevertec.adapter.output.persistence.adapter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.clevertec.adapter.output.persistence.PersistenceUserMapper;
import ru.clevertec.adapter.output.persistence.jpa.entity.UserEntity;
import ru.clevertec.adapter.output.persistence.jpa.repository.UserRepository;
import ru.clevertec.port.output.WriteUserPort;
import ru.clevertec.port.output.port.UserPortResult;
import ru.clevertec.port.output.port.UserRegisterPortCommand;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class WriteUserAdapter implements WriteUserPort {

    private final UserRepository userRepository;
    private final PersistenceUserMapper userMapper;

    @Override
    public UserPortResult createOrUpdateUser(UserRegisterPortCommand userRegisterPortCommand) {
        log.info("WriteUserAdapter.createOrUpdateUser: Creating or updating user. command: {}",
                userRegisterPortCommand);
        Optional<UserEntity> optionalUserEntity
                = userRepository.findByKeycloakUserId(userRegisterPortCommand.keycloakUserId());
        UserPortResult userPortResult;
        if (optionalUserEntity.isEmpty()) {
            userPortResult = userMapper.toUserPortResult(userRepository.save(
                    userMapper.toUserEntity(userRegisterPortCommand))
            );
        } else {
            userPortResult = userMapper.toUserPortResult(optionalUserEntity.get());
        }
        log.info("WriteUserAdapter.createOrUpdateUser: User created or updated successfully. Result: {}",
                userPortResult);
        return userPortResult;
    }
}

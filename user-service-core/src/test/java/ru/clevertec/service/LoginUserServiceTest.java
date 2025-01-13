package ru.clevertec.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.cache.Cache;
import ru.clevertec.domain.User;
import ru.clevertec.mapper.DomainUserMapper;
import ru.clevertec.port.input.command.UserUseCaseResult;
import ru.clevertec.port.output.ReadUserPort;
import ru.clevertec.port.output.port.UserPortResult;
import ru.clevertec.util.TestData;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginUserServiceTest {

    @Mock
    ReadUserPort userPort;

    @Mock
    DomainUserMapper userMapper;

    @Mock
    Cache<UUID, User> cache;

    @InjectMocks
    LoginUserService loginUserService;

    @Test
    void shouldReadUserByKeycloakUserId_whenUserExistInCache() {
        //given
        UUID keycloakUserId = UUID.randomUUID();
        User user = TestData.generateUser();
        UserUseCaseResult userUseCaseResult = TestData.generateUserUseCaseResult();

        when(cache.get(keycloakUserId))
                .thenReturn(Optional.of(user));
        when(userMapper.toUserUseCaseResult(user))
                .thenReturn(userUseCaseResult);

        //when
        UserUseCaseResult actualUserUseCaseResult = loginUserService.readUserByKeycloakUserId(keycloakUserId);

        //then
        assertEquals(userUseCaseResult, actualUserUseCaseResult);
        verify(cache).get(any());
        verify(userMapper).toUserUseCaseResult(any(User.class));
        verify(userPort, never()).readUserByKeycloakUserId(any());
        verify(cache, never()).put(any(), any());
    }

    @Test
    void shouldReadUserByKeycloakUserId_whenUserNotExistInCache() {
        //given
        UUID keycloakUserId = UUID.randomUUID();
        User user = TestData.generateUser();
        UserPortResult userPortResult = TestData.generateUserPortResult();
        UserUseCaseResult userUseCaseResult = TestData.generateUserUseCaseResult();

        when(userPort.readUserByKeycloakUserId(keycloakUserId))
                .thenReturn(userPortResult);
        when(userMapper.toUser(userPortResult))
                .thenReturn(user);
        when(userMapper.toUserUseCaseResult(user))
                .thenReturn(userUseCaseResult);

        //when
        UserUseCaseResult actualUserUseCaseResult = loginUserService.readUserByKeycloakUserId(keycloakUserId);

        //then
        assertEquals(userUseCaseResult, actualUserUseCaseResult);
        verify(userPort).readUserByKeycloakUserId(any());
        verify(userMapper).toUser(any(UserPortResult.class));
        verify(userMapper).toUserUseCaseResult(any(User.class));
    }
}
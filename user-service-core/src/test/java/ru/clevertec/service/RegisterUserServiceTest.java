package ru.clevertec.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.cache.Cache;
import ru.clevertec.domain.User;
import ru.clevertec.mapper.DomainUserMapper;
import ru.clevertec.port.input.command.UserRegisterCommand;
import ru.clevertec.port.input.command.UserUseCaseResult;
import ru.clevertec.port.output.WriteUserPort;
import ru.clevertec.port.output.port.UserPortResult;
import ru.clevertec.port.output.port.UserRegisterPortCommand;
import ru.clevertec.util.TestData;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RegisterUserServiceTest {

    @Mock
    WriteUserPort userPort;

    @Mock
    DomainUserMapper userMapper;

    @Mock
    Cache<UUID, User> cache;

    @InjectMocks
    RegisterUserService registerUserService;

    @Test
    void shouldCreateUser() {
        //given
        User user = TestData.generateUser();
        UserPortResult userPortResult = TestData.generateUserPortResult();
        UserUseCaseResult userUseCaseResult = TestData.generateUserUseCaseResult();
        UserRegisterCommand userRegisterCommand = TestData.generateUserRegisterCommand();
        UserRegisterPortCommand userRegisterPortCommand = TestData.generateUserRegisterPortCommand();

        when(userMapper.toUser(userRegisterCommand))
                .thenReturn(user);
        when(userMapper.toUserRegisterPortCommand(user))
                .thenReturn(userRegisterPortCommand);
        when(userPort.createOrUpdateUser(userRegisterPortCommand))
                .thenReturn(userPortResult);
        when(userMapper.toUserUseCaseResult(userPortResult))
                .thenReturn(userUseCaseResult);

        //when
        UserUseCaseResult actualUserUseCaseResult = registerUserService.createUser(userRegisterCommand);

        //then
        assertEquals(userUseCaseResult, actualUserUseCaseResult);
        verify(userMapper).toUser(any(UserRegisterCommand.class));
        verify(userMapper).toUserRegisterPortCommand(any());
        verify(userPort).createOrUpdateUser(any());
        verify(userMapper).toUserUseCaseResult(any(UserPortResult.class));
        verify(cache).put(any(), any());
    }
}
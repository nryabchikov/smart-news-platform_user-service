package ru.clevertec.adapter.output.persistence.adapter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.adapter.output.persistence.PersistenceUserMapper;
import ru.clevertec.adapter.output.persistence.jpa.entity.UserEntity;
import ru.clevertec.adapter.output.persistence.jpa.repository.UserRepository;
import ru.clevertec.port.output.port.UserPortResult;
import ru.clevertec.port.output.port.UserRegisterPortCommand;
import ru.clevertec.util.TestData;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WriteUserAdapterTest {

    @Mock
    UserRepository userRepository;

    @Mock
    PersistenceUserMapper userMapper;

    @InjectMocks
    WriteUserAdapter writeUserAdapter;

    @Test
    void shouldNotCreateUser_whenUserExist() {
        //given
        UserEntity userEntity = TestData.generateUserEntity();
        UserPortResult userPortResult = TestData.generateUserPortResult();
        UserRegisterPortCommand userRegisterPortCommand = TestData.generateUserRegisterPortCommand();

        when(userRepository.findByKeycloakUserId(userRegisterPortCommand.keycloakUserId()))
                .thenReturn(Optional.of(userEntity));
        when(userMapper.toUserPortResult(userEntity))
                .thenReturn(userPortResult);

        //when
        UserPortResult actualUserPortResult = writeUserAdapter.createOrUpdateUser(userRegisterPortCommand);

        //then
        assertEquals(userPortResult, actualUserPortResult);
        verify(userRepository).findByKeycloakUserId(any());
        verify(userMapper).toUserPortResult(any());
        verify(userRepository, never()).save(any());
    }

    @Test
    void shouldCreateUser_whenUserNotExist() {
        //given
        UserEntity userEntity = TestData.generateUserEntity();
        UserPortResult userPortResult = TestData.generateUserPortResult();
        UserRegisterPortCommand userRegisterPortCommand = TestData.generateUserRegisterPortCommand();

        when(userRepository.findByKeycloakUserId(userRegisterPortCommand.keycloakUserId()))
                .thenReturn(Optional.empty());
        when(userMapper.toUserEntity(userRegisterPortCommand))
                .thenReturn(userEntity);
        when(userRepository.save(userEntity))
                .thenReturn(userEntity);
        when(userMapper.toUserPortResult(userEntity))
                .thenReturn(userPortResult);

        //when
        UserPortResult actualUserPortResult = writeUserAdapter.createOrUpdateUser(userRegisterPortCommand);

        //then
        assertEquals(userPortResult, actualUserPortResult);
        verify(userRepository).findByKeycloakUserId(any());
        verify(userMapper).toUserEntity(any());
        verify(userRepository).save(any());
        verify(userMapper).toUserPortResult(any());
    }
}
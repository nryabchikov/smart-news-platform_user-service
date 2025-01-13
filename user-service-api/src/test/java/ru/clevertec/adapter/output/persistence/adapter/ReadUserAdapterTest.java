package ru.clevertec.adapter.output.persistence.adapter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.adapter.output.persistence.PersistenceUserMapper;
import ru.clevertec.adapter.output.persistence.jpa.entity.UserEntity;
import ru.clevertec.adapter.output.persistence.jpa.repository.UserRepository;
import ru.clevertec.exception.UserNotFoundException;
import ru.clevertec.port.output.port.UserPortResult;
import ru.clevertec.util.TestData;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReadUserAdapterTest {

    @Mock
    UserRepository userRepository;

    @Mock
    PersistenceUserMapper userMapper;

    @InjectMocks
    ReadUserAdapter readUserAdapter;

    @Test
    void shouldReadUserByKeycloakUserId_whenUserExist() {
        //given
        UUID keycloakUserId = UUID.randomUUID();
        UserEntity userEntity = TestData.generateUserEntity();
        UserPortResult userPortResult = TestData.generateUserPortResult();

        when(userRepository.findByKeycloakUserId(keycloakUserId))
                .thenReturn(Optional.of(userEntity));
        when(userMapper.toUserPortResult(userEntity))
                .thenReturn(userPortResult);

        //when
        UserPortResult actualUserPortResult = readUserAdapter.readUserByKeycloakUserId(keycloakUserId);

        //then
        assertEquals(userPortResult, actualUserPortResult);
        verify(userRepository).findByKeycloakUserId(any());
        verify(userMapper).toUserPortResult(any());
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    void shouldNotReadUserByKeycloakUserId_whenUserNotExist() {
        //given
        UUID keycloakUserId = UUID.randomUUID();

        doThrow(UserNotFoundException.class)
                .when(userRepository).findByKeycloakUserId(keycloakUserId);

        //when, then
        assertThrows(
                UserNotFoundException.class,
                () -> userRepository.findByKeycloakUserId(keycloakUserId)
        );
        verify(userRepository).findByKeycloakUserId(any());
        verify(userMapper, never()).toUserPortResult(any());
    }
}
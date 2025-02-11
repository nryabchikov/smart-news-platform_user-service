package ru.clevertec.adapter.output.persistence.jpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import ru.clevertec.adapter.output.persistence.jpa.entity.UserEntity;
import ru.clevertec.util.TestData;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ActiveProfiles("test")
@Sql(scripts = "classpath:db/data.sql")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void shouldFindByKeycloakUserId_whenUserExist() {
        //given
        UUID keycloakUserId = UUID.fromString("bc4e8f32-e59c-40a0-a037-213171ea7850");
        UserEntity userEntity = TestData.generateUserEntity();

        //when
        Optional<UserEntity> actualUserEntity = userRepository.findByKeycloakUserId(keycloakUserId);

        //then
        assertTrue(actualUserEntity.isPresent());
        assertThat(actualUserEntity.get())
                .usingRecursiveComparison()
                .isEqualTo(userEntity);
    }

    @Test
    void shouldNotFindByKeycloakUserId_whenUserNotExist() {
        //given
        UUID wrongKeycloakUserId = UUID.fromString("bc4e8f32-e59c-40a0-a037-213171ea7851");

        //when
        Optional<UserEntity> actualUserEntity = userRepository.findByKeycloakUserId(wrongKeycloakUserId);

        //then
        assertTrue(actualUserEntity.isEmpty());
    }
}
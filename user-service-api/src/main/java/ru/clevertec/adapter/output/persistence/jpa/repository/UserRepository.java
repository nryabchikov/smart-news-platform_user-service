package ru.clevertec.adapter.output.persistence.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.clevertec.adapter.output.persistence.jpa.entity.UserEntity;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByKeycloakUserId(UUID keycloakUserId);
}

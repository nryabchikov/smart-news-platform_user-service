package ru.clevertec.adapter.output.persistence;

import org.mapstruct.Mapper;
import ru.clevertec.adapter.output.persistence.jpa.entity.UserEntity;
import ru.clevertec.port.output.port.UserPortResult;
import ru.clevertec.port.output.port.UserRegisterPortCommand;

@Mapper(componentModel = "spring")
public interface PersistenceUserMapper {
    UserPortResult toUserPortResult(UserEntity userEntity);
    UserEntity toUserEntity(UserRegisterPortCommand userRegisterPortCommand);
}

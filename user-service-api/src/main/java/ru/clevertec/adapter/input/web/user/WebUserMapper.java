package ru.clevertec.adapter.input.web.user;

import org.mapstruct.Mapper;
import ru.clevertec.adapter.input.web.user.dto.UserRequest;
import ru.clevertec.adapter.input.web.user.dto.UserResponse;
import ru.clevertec.port.input.command.UserRegisterCommand;
import ru.clevertec.port.input.command.UserUseCaseResult;

@Mapper(componentModel = "spring")
public interface WebUserMapper {
    UserResponse toUserResponse(UserUseCaseResult userUseCaseResult);
    UserRegisterCommand toUserRegisterCommand(UserRequest userRequest);
}

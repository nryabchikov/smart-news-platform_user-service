package ru.clevertec.mapper;

import org.mapstruct.Mapper;
import ru.clevertec.domain.User;
import ru.clevertec.port.input.command.UserRegisterCommand;
import ru.clevertec.port.input.command.UserUseCaseResult;
import ru.clevertec.port.output.port.UserPortResult;
import ru.clevertec.port.output.port.UserRegisterPortCommand;

@Mapper(componentModel = "spring")
public interface DomainUserMapper {
    UserUseCaseResult toUserUseCaseResult(User user);
    UserUseCaseResult toUserUseCaseResult(UserPortResult userPortResult);
    User toUser(UserPortResult userPortResult);
    User toUser(UserRegisterCommand userRegisterCommand);
    UserRegisterPortCommand toUserRegisterPortCommand(User user);
}

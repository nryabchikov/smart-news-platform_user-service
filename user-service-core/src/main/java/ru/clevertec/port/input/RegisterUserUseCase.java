package ru.clevertec.port.input;

import ru.clevertec.port.input.command.UserRegisterCommand;
import ru.clevertec.port.input.command.UserUseCaseResult;

public interface RegisterUserUseCase {
    UserUseCaseResult createUser(UserRegisterCommand userRegisterCommand);
}

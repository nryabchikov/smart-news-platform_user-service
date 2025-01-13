package ru.clevertec.port.output;

import ru.clevertec.port.output.port.UserPortResult;
import ru.clevertec.port.output.port.UserRegisterPortCommand;

public interface WriteUserPort {
    UserPortResult createOrUpdateUser(UserRegisterPortCommand userRegisterPortCommand);
}

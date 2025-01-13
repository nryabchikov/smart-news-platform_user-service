package ru.clevertec.adapter.input.web.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import ru.clevertec.adapter.input.web.user.dto.UserRequest;
import ru.clevertec.adapter.input.web.user.dto.UserResponse;
import ru.clevertec.port.input.LoginUserUseCase;
import ru.clevertec.port.input.RegisterUserUseCase;
import ru.clevertec.port.input.command.UserRegisterCommand;
import ru.clevertec.port.input.command.UserUseCaseResult;
import ru.clevertec.util.TestData;

import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    LoginUserUseCase loginUserUseCase;

    @MockitoBean
    RegisterUserUseCase registerUserUseCase;

    @MockitoBean
    WebUserMapper userMapper;

    @Test
    void readUserByKeycloakId() throws Exception {
        //given
        UserResponse userResponse = TestData.generateUserResponse();
        UserUseCaseResult userUseCaseResult = TestData.generateUserUseCaseResult();
        UUID keycloakUserId = userUseCaseResult.keycloakUserId();

        when(loginUserUseCase.readUserByKeycloakUserId(keycloakUserId))
                .thenReturn(userUseCaseResult);
        when(userMapper.toUserResponse(userUseCaseResult))
                .thenReturn(userResponse);

        MockHttpServletRequestBuilder requestBuilder = get("/api/v1/users/{keycloakId}", keycloakUserId);

        //when, then
        mockMvc.perform(requestBuilder)
                .andExpectAll(status().isOk(),
                        jsonPath("$.id").value(userResponse.id().toString()),
                        jsonPath("$.keycloakUserId").value(userResponse.keycloakUserId().toString()),
                        jsonPath("$.username").value(userResponse.username()));
    }

    @Test
    void shouldCreateOrUpdateUser() throws Exception {
        UserRequest userRequest = TestData.generateUserRequest();
        UserResponse userResponse = TestData.generateUserResponse();
        UserRegisterCommand userRegisterCommand = TestData.generateUserRegisterCommand();
        UserUseCaseResult userUseCaseResult = TestData.generateUserUseCaseResult();

        when(userMapper.toUserRegisterCommand(userRequest))
                .thenReturn(userRegisterCommand);
        when(registerUserUseCase.createUser(userRegisterCommand))
                .thenReturn(userUseCaseResult);
        when(userMapper.toUserResponse(userUseCaseResult))
                .thenReturn(userResponse);

        MockHttpServletRequestBuilder requestBuilder = post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRequest));


        //when, then
        mockMvc.perform(requestBuilder)
                .andExpectAll(status().isCreated(),
                        jsonPath("$.id").value(userResponse.id().toString()),
                        jsonPath("$.keycloakUserId").value(userResponse.keycloakUserId().toString()),
                        jsonPath("$.username").value(userResponse.username()));
    }
}
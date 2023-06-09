package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.impl;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserEmployeeRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.AuthUserResponse;
import com.pragma.powerup.usermicroservice.adapters.driving.http.factory.mapper.response.IUserResponseMapper;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IUserHandler;
import com.pragma.powerup.usermicroservice.adapters.driving.http.factory.mapper.request.IUserRequestMapper;
import com.pragma.powerup.usermicroservice.configuration.security.jwt.JwtProvider;
import com.pragma.powerup.usermicroservice.domain.api.IUserServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserHandlerImpl implements IUserHandler {

    private final IUserServicePort userServicePort;
    private final IUserRequestMapper userRequestMapper;
    private final IUserResponseMapper personResponseMapper;
    private final JwtProvider jwtProvider;

    @Override
    public void saveUserOwner(UserRequestDto userRequestDto) {
        userServicePort.saveUserOwner(userRequestMapper.toUserOwner(userRequestDto));
    }

    @Override
    public void saveUserEmployee(UserEmployeeRequestDto userEmployeeRequestDto, String token) {
        userServicePort.saveUserEmployee(userRequestMapper.toUserEmployee(userEmployeeRequestDto),userRequestMapper.toUserRestaurant(userEmployeeRequestDto)
                ,jwtProvider.getIdUserFromToken(token.substring(7)),token);
    }

    @Override
    public void saveUserCustomer(UserRequestDto userRequestDto) {
        userServicePort.saveUserCustomer(userRequestMapper.toUserOwner(userRequestDto));
    }

    @Override
    public AuthUserResponse getUsuario(String numberDocument) {
        return personResponseMapper.userToAuthUserResponse(userServicePort.getUserByDocument(numberDocument));
    }

}

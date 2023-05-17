package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.AuthUserResponse;

public interface IUserHandler {
    void saveUserOwner(UserRequestDto userRequestDto);

    AuthUserResponse getUsuario (String numberDocument);
}

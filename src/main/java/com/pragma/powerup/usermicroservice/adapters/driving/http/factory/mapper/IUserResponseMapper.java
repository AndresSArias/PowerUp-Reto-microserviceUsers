package com.pragma.powerup.usermicroservice.adapters.driving.http.factory.mapper;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.AuthUserResponse;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.UserResponseDto;
import com.pragma.powerup.usermicroservice.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserResponseMapper {

    @Mapping(target = "role",source = "role.name")
    AuthUserResponse userToAuthUserResponse(User user);


}

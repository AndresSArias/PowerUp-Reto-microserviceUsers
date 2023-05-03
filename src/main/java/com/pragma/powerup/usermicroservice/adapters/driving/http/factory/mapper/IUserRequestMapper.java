package com.pragma.powerup.usermicroservice.adapters.driving.http.factory.mapper;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserRequestDto;
import com.pragma.powerup.usermicroservice.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
//Se coloca en todos los mappers así sea Request o Response
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserRequestMapper {
    //Se coloca @Mapping cuando en las clases se usan diferentes nombres en este caso al tener la id en otro componente de la clase user toca así
    @Mapping(target = "person.id", source = "idPerson")
    @Mapping(target = "role.id", source = "idRole")
    User toUser(UserRequestDto userRequestDto);
}

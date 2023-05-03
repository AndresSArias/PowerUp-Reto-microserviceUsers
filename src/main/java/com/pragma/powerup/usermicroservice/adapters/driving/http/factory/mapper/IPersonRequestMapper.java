package com.pragma.powerup.usermicroservice.adapters.driving.http.factory.mapper;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.PersonRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPersonRequestMapper {
    //Los datos entrantes de un objeti tipo PersonRequestDto empatan con la clase Person
    // por eso no se usa @Mapping
    Person toPerson(PersonRequestDto personRequestDto);
}

package com.pragma.powerup.usermicroservice.adapters.driving.http.factory.mapper.request;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserEmployeeRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.exceptions.NoFormatDataException;
import com.pragma.powerup.usermicroservice.domain.model.User;
import com.pragma.powerup.usermicroservice.domain.model.UserRestaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDate;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserRequestMapper {

    @Mapping(source = "dateBirth", target = "dateBirth", qualifiedByName = "toDate")
    User toUserOwner(UserRequestDto userRequestDto);

    @Mapping(source = "dateBirth", target = "dateBirth", qualifiedByName = "toDate")
    User toUserEmployee (UserEmployeeRequestDto userEmployeeRequestDto);

    @Mapping(source = "name", target = "user.name")
    @Mapping(source = "lastName", target = "user.lastName")
    @Mapping(source = "numberDocument", target = "user.numberDocument")
    @Mapping(source = "phone", target = "user.phone")
    @Mapping(source = "dateBirth", target = "user.dateBirth", qualifiedByName = "toDate")
    @Mapping(source = "email", target = "user.email")
    @Mapping(source = "password", target = "user.password")
    UserRestaurant toUserRestaurant (UserEmployeeRequestDto userEmployeeRequestDto);
    @Named("toDate")
    default LocalDate toDate(String dateOfBirth) throws ParseException {
        try {
            return LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        } catch (DateTimeParseException e) {
            throw new NoFormatDataException();
        }
    }


}

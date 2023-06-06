package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.mappers;


import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.UserRestaurantEntity;
import com.pragma.powerup.usermicroservice.domain.model.UserRestaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserRestaurantEntityMapper {

    @Mapping(target = "userEntity.id", source = "user.id")
    UserRestaurantEntity toEntity(UserRestaurant userRestaurant);

    @Mapping(target = "user.id", source = "userEntity.id")
    UserRestaurant toUserRestaurant(UserRestaurantEntity userRestaurant);

}

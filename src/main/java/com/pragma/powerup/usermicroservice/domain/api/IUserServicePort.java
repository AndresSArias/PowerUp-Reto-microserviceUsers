package com.pragma.powerup.usermicroservice.domain.api;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.UserEntity;
import com.pragma.powerup.usermicroservice.domain.model.User;

public interface IUserServicePort {
    UserEntity saveUserOwner(User user);

    User getUserByDocument (String numberDocument);
}

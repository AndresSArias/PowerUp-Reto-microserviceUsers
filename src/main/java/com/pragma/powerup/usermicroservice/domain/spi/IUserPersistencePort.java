package com.pragma.powerup.usermicroservice.domain.spi;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.UserEntity;
import com.pragma.powerup.usermicroservice.domain.model.User;

public interface IUserPersistencePort {
    UserEntity saveUserOwner(User user);

    User getUserByDocument(String numberDocument);

}

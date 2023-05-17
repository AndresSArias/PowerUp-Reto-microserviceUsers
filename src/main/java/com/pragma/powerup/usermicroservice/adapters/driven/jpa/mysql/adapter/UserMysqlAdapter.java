package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.UserEntity;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.*;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.IRoleRepository;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.IUserRepository;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.mappers.IUserEntityMapper;
import com.pragma.powerup.usermicroservice.domain.model.User;
import com.pragma.powerup.usermicroservice.domain.spi.IUserPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import static com.pragma.powerup.usermicroservice.configuration.Constants.*;


@RequiredArgsConstructor
@Transactional
public class UserMysqlAdapter implements IUserPersistencePort {


    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;

    private final IUserEntityMapper userEntityMapper;



    @Override
    public UserEntity saveUserOwner(User user) {

        if (!user.getRole().getId().equals(OWNER_ROLE_ID))
        {
            throw new RoleNotAllowedForCreationException();
        }
        if (userRepository.findByNumberDocument(user.getNumberDocument()).isPresent()) {
            throw new UserAlreadyExistsException();
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new MailAlreadyExistsException();
        }
        if (!roleRepository.findById(user.getRole().getId()).isPresent()){
            throw new RoleNotFoundException();
        }

        return userRepository.save(userEntityMapper.toEntity(user));
    }

    @Override
    public User getUserByDocument(String numberDocument) {
        UserEntity userEntity = userRepository.findByNumberDocument(numberDocument).orElseThrow(UserNotFoundException::new);
        return userEntityMapper.toUser(userEntity);
    }

}

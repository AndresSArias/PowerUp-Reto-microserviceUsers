package com.pragma.powerup.usermicroservice.domain.api.usecase;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.UserEntity;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.mappers.IUserEntityMapper;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IPlazoletaClient;
import com.pragma.powerup.usermicroservice.domain.api.IUserServicePort;
import com.pragma.powerup.usermicroservice.domain.exceptions.AgeNotAllowedForCreationException;
import com.pragma.powerup.usermicroservice.domain.exceptions.DNIIsSoBigException;
import com.pragma.powerup.usermicroservice.domain.exceptions.NitRestaurantException;
import com.pragma.powerup.usermicroservice.domain.exceptions.PhoneLenghtException;
import com.pragma.powerup.usermicroservice.domain.model.User;
import com.pragma.powerup.usermicroservice.domain.model.UserRestaurant;
import com.pragma.powerup.usermicroservice.domain.spi.IRolePersistencePort;
import com.pragma.powerup.usermicroservice.domain.spi.IUserPersistencePort;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

import static com.pragma.powerup.usermicroservice.configuration.Constants.*;

public class UserUseCase implements IUserServicePort {
    private final IUserPersistencePort userPersistencePort;
    private final IRolePersistencePort rolePersistencePort;
    private final IUserEntityMapper userEntityMapper;
    private final IPlazoletaClient plazoletaClient;

    private final PasswordEncoder passwordEncoder;

    public UserUseCase(IUserPersistencePort userPersistencePort, IRolePersistencePort rolePersistencePort, IUserEntityMapper userEntityMapper,IPlazoletaClient plazoletaClient,PasswordEncoder passwordEncoder) {
        this.userPersistencePort = userPersistencePort;
        this.rolePersistencePort = rolePersistencePort;
        this.userEntityMapper = userEntityMapper;
        this.plazoletaClient = plazoletaClient;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserEntity saveUserOwner(User user) {

        if (!validateAge(user.getDateBirth())) {
            throw new AgeNotAllowedForCreationException();
        }

        if (user.getNumberDocument().length() > 20){
            throw new DNIIsSoBigException();
        }

        if (!validatePhone(user)){
            throw new PhoneLenghtException();
        }


        user.setRole(rolePersistencePort.getRol(OWNER_ROLE_ID));
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userPersistencePort.saveUserOwner(user);
    }

    @Override
    public UserEntity saveUserEmployee(User user, UserRestaurant userRestaurant, String onwerDNI, String token) {
        String nitRestaurant = null;
        try {
            nitRestaurant = plazoletaClient.getIdRestaurant(userRestaurant.getNitRestaurant(), getHeaders(token)).getBody();
        }catch (Exception e){
            throw new NitRestaurantException();
        }

        userRestaurant.setNitRestaurant(nitRestaurant);

        if (!validateAge(user.getDateBirth())) {
            throw new AgeNotAllowedForCreationException();
        }

        if (user.getNumberDocument().length() > 20){
            throw new DNIIsSoBigException();
        }

        if (!validatePhone(user)){
            throw new PhoneLenghtException();
        }

        user.setRole(rolePersistencePort.getRol(EMPLOYEE_ROLE_ID));
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        UserEntity newEmployee = userPersistencePort.saveUserEmployee(user);

        userRestaurant.setUser(userEntityMapper.toUser(newEmployee));
        userRestaurant.setPosition("Employee");
        userRestaurant.setActive("true");

        userPersistencePort.saveRelationUserRestaurant(userRestaurant, newEmployee.getRoleEntity().getId());

        return newEmployee;
    }

    @Override
    public UserEntity saveUserCustomer(User user) {

        if (!validateAge(user.getDateBirth())) {
            throw new AgeNotAllowedForCreationException();
        }

        if (user.getNumberDocument().length() > 20){
            throw new DNIIsSoBigException();
        }

        if (!validatePhone(user)){
            throw new PhoneLenghtException();
        }


        user.setRole(rolePersistencePort.getRol(CUSTOMER_ROLE_ID));
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userPersistencePort.saveUserCustomer(user);
    }

    @Override
    public User getUserByDocument(String numberDocument) {
        return userPersistencePort.getUserByDocument(numberDocument);
    }

    public boolean validatePhone(User user) {
        String[] phoneComponents = user.getPhone().split(" ");
        user.setPhone("");

        int lenghtPhone = 0;
        for (int i = 0; i  < phoneComponents.length; i++){
            lenghtPhone = lenghtPhone + phoneComponents[i].length();
            user.setPhone(user.getPhone()+phoneComponents[i]);
        }

        return lenghtPhone <= 13;
    }

    public boolean validateAge(LocalDate dateBirth) {
        LocalDate dateNow = LocalDate.now();
        long age =  dateBirth.until(dateNow, ChronoUnit.YEARS);

        return age >= 18l ;
    }
    public Map<String, String> getHeaders(String token) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", token);
        return  headers;
    }
}

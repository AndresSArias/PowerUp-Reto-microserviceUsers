package com.pragma.powerup.usermicroservice.domain.api.usecase;

import com.pragma.powerup.usermicroservice.domain.api.IUserServicePort;
import com.pragma.powerup.usermicroservice.domain.exceptions.AgeNotAllowedForCreationException;
import com.pragma.powerup.usermicroservice.domain.exceptions.DNIIsSoBigException;
import com.pragma.powerup.usermicroservice.domain.exceptions.PhoneLenghtException;
import com.pragma.powerup.usermicroservice.domain.model.User;
import com.pragma.powerup.usermicroservice.domain.spi.IRolePersistencePort;
import com.pragma.powerup.usermicroservice.domain.spi.IUserPersistencePort;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static com.pragma.powerup.usermicroservice.configuration.Constants.OWNER_ROLE_ID;

public class UserUseCase implements IUserServicePort {
    private final IUserPersistencePort userPersistencePort;
    private final IRolePersistencePort rolePersistencePort;
    private final PasswordEncoder passwordEncoder;

    public UserUseCase(IUserPersistencePort userPersistencePort, IRolePersistencePort rolePersistencePort, PasswordEncoder passwordEncoder) {
        this.userPersistencePort = userPersistencePort;
        this.rolePersistencePort = rolePersistencePort;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUserOwner(User user) {

        if (!validateAge(user.getDateBirth())) {
            throw new AgeNotAllowedForCreationException();
        }

        if (user.getNumberDocument().length() > 20){
            throw new DNIIsSoBigException();
        }

        if (!validatePhone(user.getPhone())){
            throw new PhoneLenghtException();
        }


        user.setRole(rolePersistencePort.getRol(OWNER_ROLE_ID));
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userPersistencePort.saveUserOwner(user);
    }

    private boolean validatePhone(String phone) {
        String[] phoneComponents = phone.split(" ");
        int lenghtPhone = 0;
        for (int i = 0; i  < phoneComponents.length; i++){
            lenghtPhone = lenghtPhone + phoneComponents[i].length();
        }
        if (lenghtPhone > 13){
            return false;
        }
        return true;
    }

    private boolean validateAge(LocalDate dateBirth) {
        LocalDate dateNow = LocalDate.now();
        long age =  dateBirth.until(dateNow, ChronoUnit.YEARS);

        if ( age >= 18l){
            return true;
        }
        return false;
    }

}

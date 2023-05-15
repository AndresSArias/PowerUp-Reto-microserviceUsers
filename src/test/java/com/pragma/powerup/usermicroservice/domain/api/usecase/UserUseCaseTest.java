package com.pragma.powerup.usermicroservice.domain.api.usecase;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.RoleEntity;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.UserEntity;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.mappers.IUserEntityMapper;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.mappers.IUserEntityMapperImpl;
import com.pragma.powerup.usermicroservice.domain.model.Role;
import com.pragma.powerup.usermicroservice.domain.model.User;
import com.pragma.powerup.usermicroservice.domain.spi.IRolePersistencePort;
import com.pragma.powerup.usermicroservice.domain.spi.IUserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserUseCaseTest {
    @InjectMocks
    private UserUseCase userUseCase;

    @Mock
    private IUserPersistencePort userPersistencePort;
    @Mock
    private IRolePersistencePort rolePersistencePort;
    @Mock
    private PasswordEncoder passwordEncoder;

    private User requestUser;

    @BeforeEach
    void setUp() {
        requestUser = new User (1L,"nameTest", "lasNameTest","123","+573142294644"
                ,LocalDate.of(2000,1,1),"prueba@test.com","123"
                , new Role(1L,"ROLE_PRUEBA","ROLE_PRUEBA"));


    }

    @Test
    void saveUserOwner() {
        UserEntity responseUserEntity = new UserEntity(requestUser.getId(),requestUser.getName(),requestUser.getLastName()
                ,requestUser.getNumberDocument(),requestUser.getPhone(),requestUser.getDateBirth()
                , requestUser.getEmail(),"encrypyedPassword"
                , new RoleEntity(requestUser.getRole().getId(), requestUser.getRole().getName(),requestUser.getRole().getDescription()));
        when(rolePersistencePort.getRol(anyLong())).thenReturn(new Role(1L,"ROLE_PRUEBA","ROLE_PRUEBA"));
        when(passwordEncoder.encode(requestUser.getPassword())).thenReturn("encrypyedPassword");
        when(userPersistencePort.saveUserOwner(requestUser)).thenReturn(responseUserEntity);

        UserEntity responseUserEntityTest = userUseCase.saveUserOwner(requestUser);

        assertEquals(responseUserEntityTest, responseUserEntity);
    }

    @ParameterizedTest
    @CsvSource({
            "'+57 3142294643',true",
            "'+57 314 219464 3',true",
            "'+571234567890',true",
            "'+5712345678901',false",
            "'+571234567890123',false",
    })
    void validatePhone(String phone, boolean result) {
        //Preparacion
        requestUser.setPhone(phone);

        //Ejecución
        boolean obtained = userUseCase.validatePhone(requestUser);

        //Verificación
        assertEquals(result,obtained,"result was wrong");
    }
    @ParameterizedTest
    @CsvSource({
            "2000,2,1,true",
            "2020,12,25,false",
            "1999,3,29,true",
            "2015,5,14,false",
            "2005,4,25,true",
            "2005,6,25,false"

    })
    void validateAge(int year, int month, int day, boolean result) {
        //Preparacion
        LocalDate requestDateBirth = LocalDate.of(year, month, day);


        //Ejecución
        boolean obtained = userUseCase.validateAge(requestDateBirth);

        //Verificación
        assertEquals(result,obtained,"result was wrong");
    }
}
package com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class UserRequestDto {
    @NotBlank
    private String name;
    @NotBlank
    private String lastName;
    @NotBlank
    private String number_document;
    @NotBlank
    private String phone;
    @NotBlank
    private Date date_birth;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}

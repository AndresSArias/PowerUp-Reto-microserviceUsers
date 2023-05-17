package com.pragma.powerup.usermicroservice.adapters.driving.http.endpoints.controller;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.LoginRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.AuthUserResponse;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.JwtResponseDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IAuthHandler;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IUserHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IAuthHandler authHandler;
    private final IUserHandler userHandler;

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        return new ResponseEntity<>(authHandler.login(loginRequestDto), HttpStatus.OK);
    }

    @PostMapping("/refresh")
    @SecurityRequirement(name = "jwt")
    public ResponseEntity<JwtResponseDto> refresh(@RequestBody JwtResponseDto jwtResponseDto) throws ParseException {
        return new ResponseEntity<>(authHandler.refresh(jwtResponseDto), HttpStatus.OK);
    }

    @Operation(summary = "Get a user for conect MicroservicePlazoleta",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User returned",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthUserResponse.class))),
                    @ApiResponse(responseCode = "404", description = "User not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @GetMapping("/user/{numberDocument}")
    public ResponseEntity<AuthUserResponse> getEmployee(@PathVariable String numberDocument) {
        return ResponseEntity.ok(userHandler.getUsuario(numberDocument));
    }

}

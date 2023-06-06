package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.Map;

@HttpExchange(
        url = "http://localhost:8091/",
        accept = "application/json",
        contentType = "application/json"
)
public interface IPlazoletaClient {

    @GetExchange("restaurant/getIdRestaurant/{nitRestaurant}")
    ResponseEntity<String> getIdRestaurant(@PathVariable String nitRestaurant, @RequestHeader Map<String, String> headers);

}

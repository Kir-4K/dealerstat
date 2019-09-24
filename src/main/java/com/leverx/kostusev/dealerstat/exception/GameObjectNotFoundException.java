package com.leverx.kostusev.dealerstat.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(value = NOT_FOUND)
public class GameObjectNotFoundException extends RuntimeException {

    public GameObjectNotFoundException() {
        super("Game object with entered id not found!");
    }
}

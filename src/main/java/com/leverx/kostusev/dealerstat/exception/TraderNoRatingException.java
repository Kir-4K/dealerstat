package com.leverx.kostusev.dealerstat.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(value = NOT_FOUND)
public class TraderNoRatingException extends RuntimeException {

    public TraderNoRatingException() {
        super("The trader does not have a rating!");
    }
}

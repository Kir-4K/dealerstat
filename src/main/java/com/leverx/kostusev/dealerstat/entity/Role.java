package com.leverx.kostusev.dealerstat.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {

    ADMIN("Администратор"),
    TRADER("Трейдер"),
    ANONYMOUS("Аноним");

    private String name;
}

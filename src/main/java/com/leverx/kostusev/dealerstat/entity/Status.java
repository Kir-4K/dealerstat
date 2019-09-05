package com.leverx.kostusev.dealerstat.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {

    APPROVED("Одобренно"),
    DECLINED("Отказанно");

    private String name;
}

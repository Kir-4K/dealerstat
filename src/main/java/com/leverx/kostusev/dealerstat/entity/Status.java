package com.leverx.kostusev.dealerstat.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {

    UNPROCESSED("Не обработан"),
    APPROVED("Одобренно"),
    DECLINED("Отказанно");

    private String name;
}

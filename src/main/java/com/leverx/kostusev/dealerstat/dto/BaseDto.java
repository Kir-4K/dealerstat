package com.leverx.kostusev.dealerstat.dto;

import java.io.Serializable;

public interface BaseDto<T extends Serializable> {

    T getId();
}

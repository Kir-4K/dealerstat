package com.leverx.kostusev.dealerstat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameDto implements BaseDto<Long> {

    private Long id;
    private String name;
}

package com.leverx.kostusev.dealerstat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameDto implements BaseDto<Long> {

    private Long id;

    @NotBlank
    private String name;
}

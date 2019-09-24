package com.leverx.kostusev.dealerstat.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.leverx.kostusev.dealerstat.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameObjectDto implements BaseDto<Long> {

    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String text;

    private GameDto game;

    private Status status;

    @JsonFormat(pattern = "dd.MM.yyyy в HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "dd.MM.yyyy в HH:mm:ss")
    private LocalDateTime updatedAt;

    private UserDto user;
}

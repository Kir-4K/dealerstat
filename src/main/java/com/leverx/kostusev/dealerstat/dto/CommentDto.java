package com.leverx.kostusev.dealerstat.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDto implements BaseDto<Long> {

    private Long id;

    @NotBlank
    private String message;

    @NotNull
    private Integer rating;

    @JsonFormat(pattern = "dd.MM.yyyy в HH:mm:ss")
    private LocalDateTime createdAt;

    private Boolean approved;

    private UserDto user;

    private GameObjectDto gameObject;
}

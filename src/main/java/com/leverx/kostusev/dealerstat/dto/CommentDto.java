package com.leverx.kostusev.dealerstat.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.leverx.kostusev.dealerstat.entity.GameObject;
import com.leverx.kostusev.dealerstat.entity.User;
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

    private LocalDateTime createdAt;

    private Boolean approved;

    @JsonIgnore
    private User user;

    @JsonIgnore
    private GameObject gameObject;
}

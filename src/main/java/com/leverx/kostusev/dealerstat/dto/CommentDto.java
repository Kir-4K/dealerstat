package com.leverx.kostusev.dealerstat.dto;

import com.leverx.kostusev.dealerstat.entity.GameObject;
import com.leverx.kostusev.dealerstat.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDto implements BaseDto<Long> {

    private Long id;
    private String message;
    private Integer rating;
    private LocalDateTime createdAt;
    private Boolean approved;
    private User user;
    private GameObject gameObject;
}

package com.leverx.kostusev.dealerstat.dto;

import com.leverx.kostusev.dealerstat.entity.Game;
import com.leverx.kostusev.dealerstat.entity.Status;
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
public class GameObjectDto implements BaseDto<Long> {

    private Long id;
    private String title;
    private String text;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private User user;
    private Game game;
}

package com.leverx.kostusev.dealerstat.dto;

import com.leverx.kostusev.dealerstat.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto implements BaseDto<Long> {

    private Long id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private LocalDateTime createdAt;
    private Role role;
}

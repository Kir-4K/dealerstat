package com.leverx.kostusev.dealerstat.dto;

import com.leverx.kostusev.dealerstat.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto implements BaseDto<Long> {

    private Long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    @Size(min = 4)
    private String password;

    @NotBlank
    @Email
    private String email;

    private LocalDateTime createdAt;

    private Role role;
}

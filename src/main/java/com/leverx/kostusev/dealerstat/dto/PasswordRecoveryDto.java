package com.leverx.kostusev.dealerstat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PasswordRecoveryDto {

    @NotBlank
    private String token;

    @Email
    private String email;

    @NotBlank
    private String password;
}

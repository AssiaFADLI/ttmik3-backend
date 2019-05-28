package com.viseo.ttmik.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

    @NotEmpty
    private String login;

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;
}

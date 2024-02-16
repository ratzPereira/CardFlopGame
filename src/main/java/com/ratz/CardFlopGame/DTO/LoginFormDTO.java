package com.ratz.CardFlopGame.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginFormDTO {

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Invalid email. Please, enter a valid email")
    private String email;

    @NotEmpty(message = "Password cannot be empty")
    private String password;
}

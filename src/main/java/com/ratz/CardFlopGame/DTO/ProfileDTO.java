package com.ratz.CardFlopGame.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProfileDTO {

    @NotEmpty(message = "First name cannot be empty")
    private String firstName;

    @NotEmpty(message = "Last name cannot be empty")
    private String lastName;

    private String bio;
    private String avatarUrl;
    private String location;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;
}

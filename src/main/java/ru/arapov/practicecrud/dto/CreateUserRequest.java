package ru.arapov.practicecrud.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserRequest(

        @NotBlank(message = "username is required")
        @Size(min = 2, max = 15, message = "Username must be between 2 and 15 characters")
        String username,

        @NotBlank(message = "Email is required")
        @Email(message = "Email should be valid")
        String email
) {
}

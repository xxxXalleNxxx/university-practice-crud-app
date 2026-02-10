package ru.arapov.practicecrud.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateForUserRequest(
        @NotBlank(message = "title is required")
        @Size(min = 2, max = 15, message = "title must be between 5 and 25 characters")
        String title,

        @NotBlank(message = "description is required")
        @Size(min = 2, max = 15, message = "description must be between 15 and 100 characters")
        String description
) {
}

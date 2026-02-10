package ru.arapov.practicecrud.dto;

import jakarta.validation.constraints.Size;

public record UpdateForUserRequest(

        @Size(min = 5, max = 15, message = "title must be between 5 and 15 characters")
        String title,

        @Size(min = 15, max = 100, message = "description must be between 15 and 100 characters")
        String description
) {
}

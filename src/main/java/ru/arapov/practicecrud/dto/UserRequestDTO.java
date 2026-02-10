package ru.arapov.practicecrud.dto;

import ru.arapov.practicecrud.model.UserRequest;

public record UserRequestDTO(
        Long id,
        String title,
        String description
) {

    public static UserRequestDTO from(UserRequest request) {
        return new UserRequestDTO(
                request.getId(),
                request.getTitle(),
                request.getDescription()
        );
    }
}

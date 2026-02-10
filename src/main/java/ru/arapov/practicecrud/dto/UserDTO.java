package ru.arapov.practicecrud.dto;

import ru.arapov.practicecrud.model.User;

import java.util.List;

public record UserDTO (
        Long id,
        String username,
        String email,
        List<UserRequestDTO> requestDTO
) {

    public static UserDTO from(User user) {
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRequest().stream()
                        .map(UserRequestDTO::from)
                        .toList()
        );
    }
}

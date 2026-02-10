package ru.arapov.practicecrud.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.arapov.practicecrud.dto.CreateUserRequest;
import ru.arapov.practicecrud.dto.UserDTO;
import ru.arapov.practicecrud.model.User;
import ru.arapov.practicecrud.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDTO createUser(CreateUserRequest request) {

        User savedUser = new User();
        savedUser.setUsername(request.username());
        savedUser.setEmail(request.email());

        userRepository.save(savedUser);

        return UserDTO.from(savedUser);
    }

    public UserDTO getUserById(Long id) {
        return UserDTO.from(userRepository.findUserById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id - " + id)));
    }
}

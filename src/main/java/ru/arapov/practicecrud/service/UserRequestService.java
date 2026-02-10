package ru.arapov.practicecrud.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.arapov.practicecrud.dto.CreateForUserRequest;
import ru.arapov.practicecrud.dto.UpdateForUserRequest;
import ru.arapov.practicecrud.dto.UserRequestDTO;
import ru.arapov.practicecrud.model.UserRequest;
import ru.arapov.practicecrud.repository.UserRequestRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRequestService {

    private final UserRequestRepository userRequestRepository;

    public UserRequestDTO createUserRequest(CreateForUserRequest request) {
        UserRequest savedRequest = new UserRequest();
        savedRequest.setTitle(request.title());
        savedRequest.setDescription(request.description());

        userRequestRepository.save(savedRequest);
        return UserRequestDTO.from(savedRequest);
    }

    public List<UserRequestDTO> getAllUserRequests() {
        return userRequestRepository.findAll().stream()
                .map(UserRequestDTO::from)
                .toList();
    }

    public UserRequestDTO getUserRequestById(Long id) {
        UserRequest userRequest = userRequestRepository.findUserRequestById(id)
                .orElseThrow(() -> new RuntimeException("Request not found with id - " + id));
        return UserRequestDTO.from(userRequest);
    }

    public UserRequestDTO updateUserRequest(Long id, UpdateForUserRequest request) {
        UserRequest userRequest = userRequestRepository.findUserRequestById(id)
                .orElseThrow(() -> new RuntimeException("Request not found with id - " + id));

        if (userRequest.getTitle() != null) {
            userRequest.setTitle(request.title());
        }

        if (userRequest.getDescription() != null) {
            userRequest.setDescription(request.description());
        }

        UserRequest updatedRequest = userRequestRepository.save(userRequest);
        return UserRequestDTO.from(updatedRequest);
    }

    public void deleteUserRequest(Long id) {
        UserRequest userRequest = userRequestRepository.findUserRequestById(id)
                .orElseThrow(() -> new RuntimeException("Request not found with id - " + id));

        userRequestRepository.delete(userRequest);
    }
}

package ru.arapov.practicecrud.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.arapov.practicecrud.dto.CreateForUserRequest;
import ru.arapov.practicecrud.dto.UpdateForUserRequest;
import ru.arapov.practicecrud.dto.UserRequestDTO;
import ru.arapov.practicecrud.service.UserRequestService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/requests")
@RequiredArgsConstructor
public class UserRequestController {

    private final UserRequestService userRequestService;

    @PostMapping("/create")
    public ResponseEntity<UserRequestDTO> createUserRequest(@RequestBody CreateForUserRequest request) {
        UserRequestDTO savedRequest = userRequestService.createUserRequest(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRequest);
    }

    @GetMapping
    public ResponseEntity<List<UserRequestDTO>> getAllUserRequests() {
        return ResponseEntity.ok(userRequestService.getAllUserRequests());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRequestDTO> getUserRequestById(@PathVariable Long id) {
        return ResponseEntity.ok(userRequestService.getUserRequestById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserRequestDTO> updateUserRequest(@PathVariable Long id, @RequestBody @Valid UpdateForUserRequest request) {
        UserRequestDTO updatedRequest = userRequestService.updateUserRequest(id, request);
        return ResponseEntity.ok(updatedRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserRequest(@PathVariable Long id) {
        userRequestService.deleteUserRequest(id);
        return ResponseEntity.noContent().build();
    }
}

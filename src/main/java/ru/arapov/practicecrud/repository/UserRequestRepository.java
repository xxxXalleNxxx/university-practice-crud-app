package ru.arapov.practicecrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.arapov.practicecrud.model.UserRequest;

import java.util.Optional;

@Repository
public interface UserRequestRepository extends JpaRepository<UserRequest, Long> {

    Optional<UserRequest> findUserRequestById(Long id);
}

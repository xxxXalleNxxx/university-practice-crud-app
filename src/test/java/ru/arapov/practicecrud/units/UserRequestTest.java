package ru.arapov.practicecrud.units;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.arapov.practicecrud.model.User;
import ru.arapov.practicecrud.model.UserRequest;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserRequestTest {

    private UserRequest userRequest;
    private User author;

    @BeforeEach
    void setUp() {
        userRequest = new UserRequest();
        author = new User();
        author.setId(1L);
        author.setUsername("testuser");
    }

    @Test
    void testDefaultConstructor() {
        assertNotNull(userRequest);
        assertNull(userRequest.getId());
        assertNull(userRequest.getTitle());
        assertNull(userRequest.getDescription());
        assertNull(userRequest.getAuthor());
    }

    @Test
    void testAllArgsConstructor() {
        UserRequest request = new UserRequest(1L, "Test Title", "Test Description", author);

        assertEquals(1L, request.getId());
        assertEquals("Test Title", request.getTitle());
        assertEquals("Test Description", request.getDescription());
        assertSame(author, request.getAuthor());
    }

    @Test
    void testRelationshipWithUser() {
        userRequest.setId(1L);
        userRequest.setTitle("Help Request");
        userRequest.setDescription("Need help with configuration");
        userRequest.setAuthor(author);

        assertSame(author, userRequest.getAuthor());
        assertEquals(1L, userRequest.getAuthor().getId());
        assertEquals("testuser", userRequest.getAuthor().getUsername());

        User newAuthor = new User();
        newAuthor.setId(2L);
        newAuthor.setUsername("arapov");

        userRequest.setAuthor(newAuthor);
        assertSame(newAuthor, userRequest.getAuthor());
        assertEquals(2L, userRequest.getAuthor().getId());
        assertEquals("arapov", userRequest.getAuthor().getUsername());
    }

    @Test
    void testRequestWithoutAuthor() {
        userRequest.setId(1L);
        userRequest.setTitle("Request without author");
        userRequest.setDescription("This request has no author");
        userRequest.setAuthor(null);

        assertNull(userRequest.getAuthor());
        assertEquals(1L, userRequest.getId());
        assertEquals("Request without author", userRequest.getTitle());
    }

    @Test
    void testEdgeCases() {
        userRequest.setTitle("");
        userRequest.setDescription("");

        assertEquals("", userRequest.getTitle());
        assertEquals("", userRequest.getDescription());

        userRequest.setTitle(null);
        userRequest.setDescription(null);

        assertNull(userRequest.getTitle());
        assertNull(userRequest.getDescription());
    }
}

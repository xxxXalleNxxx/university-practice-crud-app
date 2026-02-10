package ru.arapov.practicecrud.units;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.arapov.practicecrud.model.User;
import ru.arapov.practicecrud.model.UserRequest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
    }

    @Test
    void testDefaultConstructor() {
        assertNotNull(user);
        assertNull(user.getId());
        assertNull(user.getUsername());
        assertNull(user.getEmail());
        assertNotNull(user.getRequest());
        assertEquals(0, user.getRequest().size());
    }

    @Test
    void testAllArgsConstructor() {
        List<UserRequest> requests = new ArrayList<>();
        UserRequest mockRequest = mock(UserRequest.class);
        requests.add(mockRequest);

        User user = new User(1L, "testuser", "test@example.com", requests);

        assertEquals(1L, user.getId());
        assertEquals("testuser", user.getUsername());
        assertEquals("test@example.com", user.getEmail());
        assertSame(requests, user.getRequest());
        assertEquals(1, user.getRequest().size());
        assertTrue(user.getRequest().contains(mockRequest));
    }

    @Test
    void testSettersAndGetters() {
        user.setId(1L);
        user.setUsername("arapov");
        user.setEmail("arapov@example.com");

        List<UserRequest> requests = new ArrayList<>();
        UserRequest mockRequest = mock(UserRequest.class);
        requests.add(mockRequest);
        user.setRequest(requests);

        assertEquals(1L, user.getId());
        assertEquals("arapov", user.getUsername());
        assertEquals("arapov@example.com", user.getEmail());
        assertEquals(1, user.getRequest().size());
        assertSame(requests, user.getRequest());
        assertTrue(user.getRequest().contains(mockRequest));
    }

    @Test
    void testAddRequest() {
        UserRequest request1 = new UserRequest();
        request1.setTitle("Request 1");

        UserRequest request2 = new UserRequest();
        request2.setTitle("Request 2");

        user.getRequest().add(request1);
        user.getRequest().add(request2);

        assertEquals(2, user.getRequest().size());
        assertEquals("Request 1", user.getRequest().get(0).getTitle());
        assertEquals("Request 2", user.getRequest().get(1).getTitle());
    }

    @Test
    void testBidirectionalRelationship() {
        UserRequest request1 = new UserRequest();
        request1.setId(1L);
        request1.setTitle("Request 1");

        UserRequest request2 = new UserRequest();
        request2.setId(2L);
        request2.setTitle("Request 2");

        request1.setAuthor(user);
        request2.setAuthor(user);

        List<UserRequest> requests = new ArrayList<>();
        requests.add(request1);
        requests.add(request2);
        user.setRequest(requests);

        assertSame(user, request1.getAuthor());
        assertSame(user, request2.getAuthor());
        assertEquals(2, user.getRequest().size());
        assertTrue(user.getRequest().contains(request1));
        assertTrue(user.getRequest().contains(request2));
    }
}

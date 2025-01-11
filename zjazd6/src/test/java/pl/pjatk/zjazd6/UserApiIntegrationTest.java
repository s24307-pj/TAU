package pl.pjatk.zjazd6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import pl.pjatk.zjazd6.model.User;
import pl.pjatk.zjazd6.Service.UserService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserApiIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private UserService userService;

    @Autowired
    private TestRestTemplate restTemplate;

    private String buildUrl(String endpoint) {
        return "http://localhost:" + port + endpoint;
    }

    @BeforeEach
    public void clearUsers() {
        userService.getAllUsers().forEach(user -> userService.deleteUser(user.getUserId()));
    }

    @Test
    public void shouldReturnAllUsers() {
        userService.addUser(new User(null, "John Doe", "john@doe.com"));
        userService.addUser(new User(null, "Jane Doe", "jane@doe.com"));

        ResponseEntity<String> response = restTemplate.getForEntity(buildUrl("/api/v1/users"), String.class);

        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().contains("John Doe"));
        assertTrue(response.getBody().contains("Jane Doe"));
    }

    @Test
    public void shouldReturnEmptyListWhenNoUsers() {
        ResponseEntity<String> response = restTemplate.getForEntity(buildUrl("/api/v1/users"), String.class);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("[]", response.getBody());
    }

    @Test
    public void shouldReturnUserById() {
        User newUser = userService.addUser(new User(null, "John Doe", "john@doe.com"));
        Long userId = newUser.getUserId();

        ResponseEntity<User> response = restTemplate.getForEntity(buildUrl("/api/v1/users/" + userId), User.class);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("John Doe", response.getBody().getFullName());

        ResponseEntity<String> errorResponse = restTemplate.getForEntity(buildUrl("/api/v1/users/999"), String.class);
        assertEquals(404, errorResponse.getStatusCodeValue());
    }

    @Test
    public void shouldCreateUserWithValidData() {
        User newUser = new User(null, "John Doe", "john@doe.com");

        ResponseEntity<User> response = restTemplate.postForEntity(buildUrl("/api/v1/users"), newUser, User.class);

        assertEquals(201, response.getStatusCodeValue());
        assertNotNull(response.getBody().getUserId());
        assertEquals("John Doe", response.getBody().getFullName());
        assertEquals("john@doe.com", response.getBody().getEmailAddress());
    }

    @Test
    public void shouldReturnBadRequestWhenInvalidData() {
        User invalidUser = new User(null, "John Doe", "");  // Invalid email

        ResponseEntity<String> response = restTemplate.postForEntity(buildUrl("/api/v1/users"), invalidUser, String.class);

        assertEquals(400, response.getStatusCodeValue());
    }


    @Test
    public void shouldUpdateUserDetails() {
        User existingUser = userService.addUser(new User(null, "John Doe", "john@doe.com"));
        Long userId = existingUser.getUserId();

        User updatedUser = new User(userId, "John Updated", "updated@john.com");
        restTemplate.put(buildUrl("/api/v1/users/" + userId), updatedUser);

        ResponseEntity<User> response = restTemplate.getForEntity(buildUrl("/api/v1/users/" + userId), User.class);
        assertEquals("John Updated", response.getBody().getFullName());
        assertEquals("updated@john.com", response.getBody().getEmailAddress());
    }

    @Test
    public void shouldReturnNotFoundWhenUserDoesNotExistForUpdate() {
        User nonExistentUser = new User(999L, "Non Existent", "nonexistent@user.com");

        restTemplate.put(buildUrl("/api/v1/users/999"), nonExistentUser);

        ResponseEntity<String> response = restTemplate.getForEntity(buildUrl("/api/v1/users/999"), String.class);
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void shouldDeleteUser() {
        User userToDelete = userService.addUser(new User(null, "John Doe", "john@doe.com"));
        Long userId = userToDelete.getUserId();

        restTemplate.delete(buildUrl("/api/v1/users/" + userId));

        ResponseEntity<String> response = restTemplate.getForEntity(buildUrl("/api/v1/users/" + userId), String.class);
        assertEquals(404, response.getStatusCodeValue());
    }
}

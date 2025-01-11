package pl.pjatk.zjazd6.Service;

import org.springframework.stereotype.Service;
import pl.pjatk.zjazd6.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final List<User> userList;
    private final AtomicLong userIdCounter;

    public UserService() {
        this.userList = new ArrayList<>();
        this.userIdCounter = new AtomicLong(1);
    }

    public List<User> getAllUsers() {
        return userList.stream().collect(Collectors.toList());
    }

    public Optional<User> findUserById(Long userId) {
        return userList.stream().filter(user -> user.getUserId().equals(userId)).findFirst();
    }

    public User addUser(User user) {
        Long id = userIdCounter.getAndIncrement();
        User newUser = new User(id, user.getFullName(), user.getEmailAddress());
        userList.add(newUser);
        return newUser;
    }

    public Optional<User> updateUser(Long userId, User user) {
        return findUserById(userId).map(existingUser -> {
            existingUser.setFullName(user.getFullName());
            existingUser.setEmailAddress(user.getEmailAddress());
            return existingUser;
        });
    }

    public boolean deleteUser(Long userId) {
        return userList.removeIf(user -> user.getUserId().equals(userId));
    }
}

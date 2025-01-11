package pl.pjatk.zjazd6.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class User {

    private Long userId;

    @NotBlank
    private String fullName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String emailAddress;

    public User(Long userId, String fullName, String emailAddress) {
        this.userId = userId;
        this.fullName = fullName;
        this.emailAddress = emailAddress;
    }

    public Long getUserId() {
        return userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}

package framework.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserData {
    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    @JsonProperty("role")
    private String role;

    @JsonProperty("expectSuccess")
    private boolean expectSuccess;

    @JsonProperty("description")
    private String description;

    // Getters
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getRole() { return role; }
    public boolean isExpectSuccess() { return expectSuccess; }
    public String getDescription() { return description; }
}

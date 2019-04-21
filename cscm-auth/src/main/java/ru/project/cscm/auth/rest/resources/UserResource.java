package ru.project.cscm.auth.rest.resources;

import java.util.Objects;
import java.util.function.Function;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import ru.project.cscm.auth.core.CscmUser;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResource {

    @NotEmpty
    private final String username;
    
    @NotEmpty
    private final String password;
    
    @NotEmpty
    private final String name;
    
    private final boolean isActive;

    @JsonCreator
    private UserResource(
            @JsonProperty("username") final String username, 
            @JsonProperty("password") final String password,
            @JsonProperty("name") final String name,
            @JsonProperty("isActive") final boolean isActive) {
        super();
        this.username = Objects.requireNonNull(username);
        this.password = Objects.requireNonNull(password);
        this.name = Objects.requireNonNull(name);
        this.isActive = isActive;
    }
    
    public UserResource(@NotNull final CscmUser user) {
        this(user.getUsername(), "", user.getName(), user.isActive());
    }

    @NotNull
    @NotEmpty
    public String getUsername() {
        return this.username;
    }

    @NotNull
    @NotEmpty
    public String getName() {
        return this.name;
    }

    @NotNull
    @NotEmpty
    public String getPassword() {
        return this.password;
    }

    public boolean isActive() {
        return this.isActive;
    }

    public static final Function<UserResource, CscmUser> convertToUser = resource -> new CscmUser(resource.username,
            resource.password, resource.name, resource.isActive);

}

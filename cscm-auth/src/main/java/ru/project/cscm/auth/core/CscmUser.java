package ru.project.cscm.auth.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.google.common.base.Objects;

@Entity
@Table(name = "t_cscm_user")
public class CscmUser {

    @Id
    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "active", nullable = false)
    private boolean isActive;

    protected CscmUser() {
        super();
    }

    public CscmUser(@NotEmpty final String username, @NotEmpty final String password, @NotEmpty final String name, final boolean isActive) {
        super();
        this.username = username;
        this.password = password;
        this.isActive = isActive;
        this.name = name;
    }

    public final boolean isActive() {
        return isActive;
    }

    @NotNull
    @NotEmpty
    public final String getUsername() {
        return username;
    }

    @NotNull
    @NotEmpty
    public final String getPassword() {
        return password;
    }

    @NotNull
    @NotEmpty
    public final String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(username);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof CscmUser)) {
            return false;
        }

        final CscmUser other = (CscmUser) obj;
        if (!Objects.equal(username, other.username)) {
            return false;
        }

        return true;
    }
}

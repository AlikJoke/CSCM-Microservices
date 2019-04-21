package ru.project.cscm.dto.items.user;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public final class UserItem {

	private final String login;
	private final String password;
	private final boolean isActive;

	@NotNull
	@NotEmpty
	public final String getLogin() {
		return login;
	}

	@NotNull
	@NotEmpty
	public final String getPassword() {
		return password;
	}

	public final boolean isActive() {
		return isActive;
	}

	public UserItem(@NotNull @NotEmpty final String login, @NotNull @NotEmpty final String password,
			final Boolean isActive) {
		super();
		this.login = login;
		this.password = password;
		this.isActive = isActive == null ? true : isActive;
	}

	@Override
	public final String toString() {
		return "UserItem [login=" + login + ", password=" + password + ", isActive=" + isActive + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		final UserItem other = (UserItem) obj;
		if (login == null) {
			if (other.login != null) {
				return false;
			}
		} else if (!login.equals(other.login)) {
			return false;
		}

		return true;
	}

}

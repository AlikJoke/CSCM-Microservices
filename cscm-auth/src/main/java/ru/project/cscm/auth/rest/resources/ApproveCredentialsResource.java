package ru.project.cscm.auth.rest.resources;

import java.io.Serializable;
import java.util.function.Function;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import ru.project.cscm.auth.core.UserCredentials;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApproveCredentialsResource implements Serializable {

	private static final long serialVersionUID = -6022752280942990199L;

	private final String username;
	private final String password;

	@JsonCreator
	public ApproveCredentialsResource(
			@JsonProperty("username") final String username,
			@JsonProperty("password") final String password) {
		super();
		this.username = username;
		this.password = password;
	}

	@NotNull
	@NotEmpty
	public String getUsername() {
		return username;
	}

	@NotNull
	@NotEmpty
	public String getPassword() {
		return password;
	}

	public static final Function<ApproveCredentialsResource, UserCredentials> convertToUserCredentials = resource -> new UserCredentials(
			resource.username, resource.password);

}

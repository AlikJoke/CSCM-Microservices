package ru.project.cscm.auth.rest.resources;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import ru.project.cscm.auth.core.UserCredentialsApproveHandler;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class ApproveResultResource implements Serializable {

	private static final long serialVersionUID = -2136161354685803960L;

	private final boolean isApproved;
	private final String reason;

	public ApproveResultResource(boolean isApproved, String reason) {
		super();
		this.isApproved = isApproved;
		this.reason = reason;
	}
	
	public ApproveResultResource(@NotNull final UserCredentialsApproveHandler.ApproveResult result) {
		this.isApproved = result.isApproved();
		this.reason = result.getReason();
	}

	@JsonProperty("isApproved")
	public boolean isApproved() {
		return isApproved;
	}

	@JsonProperty("reason")
	public String getReason() {
		return reason;
	}

}

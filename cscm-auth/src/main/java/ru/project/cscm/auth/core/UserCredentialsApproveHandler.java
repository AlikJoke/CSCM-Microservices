package ru.project.cscm.auth.core;

import javax.validation.constraints.NotNull;

public interface UserCredentialsApproveHandler {

	@NotNull
	ApproveResult approve(@NotNull UserCredentials credentials);
	
	interface ApproveResult {
		
		boolean isApproved();
		
		String getReason();
	}
}

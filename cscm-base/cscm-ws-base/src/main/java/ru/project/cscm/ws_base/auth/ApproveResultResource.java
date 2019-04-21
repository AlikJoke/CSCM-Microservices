package ru.project.cscm.ws_base.auth;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class ApproveResultResource implements Serializable {

    private static final long serialVersionUID = -2136161354685803960L;

    private final boolean isApproved;
    private final String reason;

    @JsonCreator
    public ApproveResultResource(@JsonProperty("isApproved") boolean isApproved, @JsonProperty("reason") String reason) {
        super();
        this.isApproved = isApproved;
        this.reason = reason;
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
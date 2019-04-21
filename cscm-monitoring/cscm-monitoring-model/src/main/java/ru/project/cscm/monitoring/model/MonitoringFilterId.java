package ru.project.cscm.monitoring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@SuppressWarnings("serial")
@Embeddable
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class MonitoringFilterId implements Serializable {

    @Column(name = "username", nullable = false)
    private String user;

    @Column(name = "id", nullable = false)
    private Integer id;

    public MonitoringFilterId(String user, Integer id) {
        super();
        this.user = user;
        this.id = id;
    }

    protected MonitoringFilterId() {
        super();
    }
}

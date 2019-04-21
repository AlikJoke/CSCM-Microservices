package ru.project.cscm.monitoring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@Entity
@Table(name = "t_cscm_currency")
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE)
@Validated
public class Currency {

    @Id
    @Column(name = "currencyId", nullable = false)
    @NotNull
    private Integer currencyId;

    @Column(name = "currencyCode", nullable = false, length = 8)
    @NotEmpty
    private String currencyCode;

    @Column(name = "description")
    @NotEmpty
    private String description;

    protected Currency() {
        super();
    }

    public Currency(@NotNull Integer currencyId, @NotEmpty String currencyCode, @NotEmpty String description) {
        super();
        this.currencyId = currencyId;
        this.currencyCode = currencyCode;
        this.description = description;
    }

    @NotNull
    public Integer getCurrencyId() {
        return currencyId;
    }

    @NotEmpty
    public String getCurrencyCode() {
        return currencyCode;
    }

    @NotEmpty
    public String getDescription() {
        return description;
    }

}

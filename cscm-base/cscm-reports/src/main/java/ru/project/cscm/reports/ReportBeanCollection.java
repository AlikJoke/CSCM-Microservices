package ru.project.cscm.reports;

import java.util.Collection;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public interface ReportBeanCollection<T> {

	@NotEmpty
	String getReportPath();

	@NotEmpty
	String getReportName();

	@NotNull
	Collection<T> getCollection();
}

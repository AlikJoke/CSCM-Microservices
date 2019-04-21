package ru.project.cscm.reports.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SimpleReportBeanCollectionCreator {

	@Value("${report.templates.dir}")
	private String reportDirectory;

	public <T> SimpleReportBeanCollection<T> createCollection(final String reportName, final Collection<T> coll) {
		return new SimpleReportBeanCollection<T>(reportName, coll) {

			@Override
			public String getReportPath() {
				return reportDirectory + "/" + reportName;
			}

		};
	}
}
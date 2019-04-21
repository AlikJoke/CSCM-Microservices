package ru.project.cscm.reports.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import ru.project.cscm.reports.ReportBeanCollection;
import ru.project.cscm.reports.ReportBuilder;

@Service
public class ReportBuilderImpl implements ReportBuilder {

	private final Map<String, JasperReport> reportsByPath = new ConcurrentHashMap<>();
	
	@Override
	public @NotNull ByteArrayResource build(@NotNull final ReportBeanCollection<?> collection, @NotNull JasperExportFormat format) throws JRException, IOException {
		return this.build(Arrays.asList(collection), format);
	}

	@NotNull
	@Override
	public ByteArrayResource build(Collection<ReportBeanCollection<?>> reports,
			@NotNull JasperExportFormat format) throws JRException, IOException {
		final List<JasperPrint> prints = reports.stream()
				.map(coll -> generatePrint(coll)).collect(Collectors.toList());
		
		final JRAbstractExporter exporter;
		if (format == JasperExportFormat.XLS) {
			exporter = new JRXlsExporter();
		} else {
			exporter = new JRPdfExporter();
		}
		
		try (final ByteArrayOutputStream out = new ByteArrayOutputStream()) {

			exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, prints);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
			exporter.exportReport();

			return new ByteArrayResource(out.toByteArray());
		}
	}
	
	private JasperPrint generatePrint(final ReportBeanCollection<?> dsCollection) {
		try {
			final JRBeanCollectionDataSource beanCollDataSource = new JRBeanCollectionDataSource(dsCollection.getCollection());
			if (!reportsByPath.containsKey(dsCollection.getReportPath())) {
				reportsByPath.put(dsCollection.getReportPath(),
						JasperCompileManager.compileReport(getClass().getResourceAsStream(dsCollection.getReportPath())));
			}
			return JasperFillManager.fillReport(reportsByPath.get(dsCollection), new HashMap<>(), beanCollDataSource);
		} catch (JRException e) {
			throw new RuntimeException(e);
		}
	}
}
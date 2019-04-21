package ru.project.cscm.ws_base.core;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.CacheControl;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import ru.project.cscm.reports.ReportBuilder;
import ru.project.cscm.reports.impl.SimpleReportBeanCollectionCreator;

import com.google.common.net.MediaType;

public abstract class ReportGenerationService extends
		ControllerWithExceptionHandler {

	public static final String PATH = "/report";

	@Autowired
	protected ReportBuilder reportBuilder;

	@Autowired
	protected SimpleReportBeanCollectionCreator creator;

	@RequestMapping(value = PATH, method = RequestMethod.OPTIONS)
	@ResponseStatus(HttpStatus.OK)
	public void doOptions(final HttpServletRequest request,
			final HttpServletResponse response) {
		response.setHeader("Allow", "GET, OPTIONS");
		if (request.getHeader("Origin") != null) {
			response.setHeader("Access-Control-Allow-Methods", "GET, OPTIONS");
		}
	}
	
	protected ResponseEntity<?> createResponse(ByteArrayResource result, String fileName) throws IOException {
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentDisposition(ContentDisposition.builder("attachment").filename(fileName).build());
		headers.setContentType(new org.springframework.http.MediaType(MediaType.MICROSOFT_EXCEL.type(), MediaType.MICROSOFT_EXCEL.subtype()));
		headers.setContentLength(result.contentLength());
		headers.setLastModified(result.lastModified());

		return ResponseEntity.ok().cacheControl(CacheControl.noCache()).headers(headers).body(result);
	}
}

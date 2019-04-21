package ru.project.cscm.integration.ws;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Controller
@ControllerAdvice
public abstract class ControllerBase {

    private static final Logger log = LoggerFactory.getLogger(ControllerBase.class);

    private static final String formatterPattern = "dd.MM.yyyy HH:mm:ss:S";
    private static final String TEXT_PLAIN = "text/plain; charset=UTF-8";
    private static final String UTF8 = "UTF-8";

    protected String printMessageWithStackTrace(String message, Exception e) {
        String st = printStackTrace(e);
        return message + "\r\n\r\n" + st;
    }

    private static String printStackTrace(Throwable e) {
        SimpleDateFormat formatter = new SimpleDateFormat(formatterPattern);
        StringBuilder sb = new StringBuilder();
        sb.append("Exception: ").append(e.getClass().getName()).append("\r\n");
        sb.append("Message: ").append(e.getMessage() == null ? "" : e.getMessage()).append("\r\n");
        sb.append("Current date: ").append(formatter.format(new Date())).append("\r\n");

        final Writer stackTrace = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(stackTrace);
        e.printStackTrace(printWriter);
        sb.append(String.format("Stack trace: %s\r\n", stackTrace.toString()));

        return sb.toString();
    }

    @ExceptionHandler(value = { HttpMessageNotReadableException.class })
    public void handleJsonException(HttpMessageNotReadableException e, HttpServletResponse response) throws IOException {
        log.error(e.getMessage(), e);
        textStatusIntoResponse(response, HttpStatus.BAD_REQUEST, this.printMessageWithStackTrace(e.getMessage(), e));
    }

    @ExceptionHandler(value = { Exception.class })
    public void handleException(Exception e, HttpServletResponse response) throws IOException {
        String message = "Internal server error " + e.getMessage();
        log.error(message, e);
        textStatusIntoResponse(response, HttpStatus.INTERNAL_SERVER_ERROR, this.printMessageWithStackTrace(message, e));
    }

    @ExceptionHandler(value = { IllegalStateException.class })
    public void handleException(IllegalStateException e, HttpServletResponse response) throws IOException {
        String message = "Operation is not allowed with entity in its current state";
        log.error(message, e);
        textStatusIntoResponse(response, HttpStatus.METHOD_NOT_ALLOWED, this.printMessageWithStackTrace(message, e));
    }

    @ExceptionHandler(value = { IllegalArgumentException.class })
    public void handleException(IllegalArgumentException e, HttpServletResponse response) throws IOException {
        String message = "Illegal argument passed to function";
        log.error(message, e);
        textStatusIntoResponse(response, HttpStatus.BAD_REQUEST, this.printMessageWithStackTrace(message, e));
    }

    private void textStatusIntoResponse(HttpServletResponse r, HttpStatus status, String message) {
        r.setStatus(status.value());
        r.setContentType(TEXT_PLAIN);

        try {
            byte[] messageBytes = message.getBytes(UTF8);
            r.setContentLength(messageBytes.length);
            r.getOutputStream().write(messageBytes);
        } catch (Exception e) {
            throw new IllegalStateException("Unable to write text response", e);
        }
    }
}

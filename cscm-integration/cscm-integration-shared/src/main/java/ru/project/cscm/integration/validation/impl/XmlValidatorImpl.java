package ru.project.cscm.integration.validation.impl;

import java.io.InputStream;
import java.net.URL;

import javax.validation.constraints.NotNull;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.xml.sax.SAXException;

import ru.project.cscm.integration.validation.XmlValidator;

@Component
public class XmlValidatorImpl implements XmlValidator {

    private static final Logger logger = LoggerFactory.getLogger(XmlValidatorImpl.class);

    @Override
    public XmlValidator.ValidationResult validate(@NotNull final InputStream xmlStream, @NotNull final String xsdPath) {

        if (StringUtils.isEmpty(xsdPath)) {
            throw new IllegalArgumentException("Xsd path must be not empty");
        } else if (xmlStream == null) {
            throw new IllegalArgumentException("Xml input stream must be not null");
        }

        final SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

        final URL xsdUrl = this.getClass().getClassLoader().getResource(xsdPath);
        if (xsdUrl == null) {
            throw new IllegalArgumentException("Path to xsd is not valid = '" + xsdPath + "'");
        }

        try {
            final Schema schema = schemaFactory.newSchema(xsdUrl);

            return validate(schema, xmlStream);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
    }

    private ValidationResult validate(final Schema schema, final InputStream xmlFile) {
        final Validator validator = schema.newValidator();

        boolean success = true;
        String additionalInfo = null;

        try {
            validator.validate(new StreamSource(xmlFile));
        } catch (Exception e) {
            logger.error("Validation xml against xsd is failed", e);

            success = false;
            additionalInfo = e.getMessage();
        }

        return new ValidationResult(success, additionalInfo);
    }

    private static class ValidationResult implements XmlValidator.ValidationResult {

        private final boolean success;
        private final String addInfo;

        private ValidationResult(boolean success, String addInfo) {
            super();
            this.success = success;
            this.addInfo = addInfo;
        }

        @Override
        public boolean success() {
            return success;
        }

        @Override
        public String addInfo() {
            return addInfo;
        }

    }
}
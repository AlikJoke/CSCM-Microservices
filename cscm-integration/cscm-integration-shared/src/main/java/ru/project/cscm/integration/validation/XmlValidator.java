package ru.project.cscm.integration.validation;

import java.io.InputStream;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public interface XmlValidator {

    @NotNull
    ValidationResult validate(@NotNull InputStream xmlStream, @NotEmpty String xsdPath);
    
    interface ValidationResult {
        
        boolean success();
        
        String addInfo();
    }
}
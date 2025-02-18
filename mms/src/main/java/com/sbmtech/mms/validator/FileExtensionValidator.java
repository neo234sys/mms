package com.sbmtech.mms.validator;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileExtensionValidator implements ConstraintValidator<ValidFileExtension, MultipartFile> {

    private List<String> extensions;

    @Override
    public void initialize(ValidFileExtension constraintAnnotation) {
        extensions = List.of(constraintAnnotation.extensions());
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext constraintValidatorContext) {
        if (file == null || file.isEmpty()) {
            return true;
        }
        var extension = FilenameUtils.getExtension(file.getOriginalFilename());
        return StringUtils.isNotBlank(extension) && extensions.contains(extension.toLowerCase());
    }
}
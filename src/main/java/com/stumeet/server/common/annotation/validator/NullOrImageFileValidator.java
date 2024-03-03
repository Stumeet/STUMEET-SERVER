package com.stumeet.server.common.annotation.validator;

import com.stumeet.server.common.util.FileUtil;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

public class NullOrImageFileValidator implements ConstraintValidator<NullOrImageFile, MultipartFile> {
    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        return FileUtil.isImageFile(value.getOriginalFilename());
    }
}

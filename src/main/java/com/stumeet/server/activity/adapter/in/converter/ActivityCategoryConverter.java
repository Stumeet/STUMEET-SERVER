package com.stumeet.server.activity.adapter.in.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.stumeet.server.activity.domain.exception.NotExistsActivityCategoryException;
import com.stumeet.server.activity.domain.model.ActivityCategory;
import com.stumeet.server.common.exception.model.EnumValidationException;
import com.stumeet.server.common.response.ErrorCode;

@Component
public class ActivityCategoryConverter implements Converter<String, ActivityCategory> {
    @Override
    public ActivityCategory convert(String source) {
        try {
            return ActivityCategory.getByName(source.toUpperCase());
        } catch (NotExistsActivityCategoryException e) {
            throw new EnumValidationException(ErrorCode.INVALID_ACTIVITY_CATEGORY_EXCEPTION, ActivityCategory.class, source);
        }
    }
}
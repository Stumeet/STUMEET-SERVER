package com.stumeet.server.activity.adapter.in.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.stumeet.server.activity.domain.exception.NotExistsActivitySortException;
import com.stumeet.server.activity.domain.model.ActivitySort;
import com.stumeet.server.common.exception.model.EnumValidationException;
import com.stumeet.server.common.response.ErrorCode;

@Component
public class ActivitySortConverter implements Converter<String, ActivitySort> {
    @Override
    public ActivitySort convert(String source) {
        try {
            return ActivitySort.getByName(source.toUpperCase());
        } catch (NotExistsActivitySortException e) {
            throw new EnumValidationException(ErrorCode.INVALID_ACTIVITY_SORT_EXCEPTION, ActivitySort.class, source);
        }
    }
}

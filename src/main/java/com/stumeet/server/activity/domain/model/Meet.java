package com.stumeet.server.activity.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

import com.stumeet.server.common.exception.model.BadRequestException;
import com.stumeet.server.common.response.ErrorCode;

@Getter
public class Meet extends Activity {

    @Builder
    protected Meet(Long id, ActivityLinkedStudy study, ActivityMember author, ActivityCategory category, String title, String content, String location, String link, LocalDateTime startDate, LocalDateTime endDate, boolean isNotice, LocalDateTime createdAt) {
        super(id, study, author, category, title, content, link, location, startDate, endDate, isNotice, createdAt);

        validateLocationNonNull(location);
    }

    private void validateLocationNonNull(String location) {
        if (location == null) {
            throw new BadRequestException(ErrorCode.LOCATION_REQUIRED_FOR_MEET_EXCEPTION);
        }
    }
}

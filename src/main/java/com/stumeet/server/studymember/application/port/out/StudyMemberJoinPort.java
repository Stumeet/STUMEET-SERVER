package com.stumeet.server.studymember.application.port.out;

import com.stumeet.server.studymember.domain.StudyMember;

public interface StudyMemberJoinPort {
    void join(StudyMember studyMember);
}

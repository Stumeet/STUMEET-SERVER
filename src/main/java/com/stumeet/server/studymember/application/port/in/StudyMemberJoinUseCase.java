package com.stumeet.server.studymember.application.port.in;

import com.stumeet.server.studymember.application.port.in.command.StudyMemberJoinCommand;

public interface StudyMemberJoinUseCase {
    void join(StudyMemberJoinCommand command);
}

package com.stumeet.server.studymember.application.port.out;

public interface AdminManagementPort {

    void delegateAdmin(Long studyId, Long adminId, Long memberId);
}

package com.stumeet.server.studymember.application.port.in;

public interface AdminDelegationUseCase {

    void delegateAdmin(Long studyId, Long adminId, Long memberId);
}

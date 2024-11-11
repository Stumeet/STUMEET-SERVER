package com.stumeet.server.studymember.adapter.out.persistence;

import com.stumeet.server.common.annotation.PersistenceAdapter;
import com.stumeet.server.studymember.application.port.out.AdminManagementPort;

import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class AdminManagementAdapter implements AdminManagementPort {

    private final JpaStudyMemberRepository jpaStudyMemberRepository;

    @Override
    public void delegateAdmin(Long studyId, Long adminId, Long memberId) {
        jpaStudyMemberRepository.removeAdmin(studyId, adminId);
        jpaStudyMemberRepository.appointAdmin(studyId, memberId);
    }
}

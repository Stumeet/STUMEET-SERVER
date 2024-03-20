package com.stumeet.server.studymember.adapter.out.persistence;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.stumeet.server.studymember.application.port.in.response.QSimpleStudyMemberResponse;
import com.stumeet.server.studymember.application.port.in.response.SimpleStudyMemberResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.stumeet.server.studymember.adapter.out.persistence.QStudyMemberJpaEntity.studyMemberJpaEntity;

@RequiredArgsConstructor
public class JpaStudyMemberRepositoryCustomImpl implements JpaStudyMemberRepositoryCustom {

    private final JPAQueryFactory query;


    @Override
    public List<SimpleStudyMemberResponse> findStudyMembersByStudyId(Long studyId) {
        return query
                .select(
                        new QSimpleStudyMemberResponse(
                                studyMemberJpaEntity.member.id,
                                studyMemberJpaEntity.member.name,
                                studyMemberJpaEntity.member.image,
                                studyMemberJpaEntity.member.region,
                                studyMemberJpaEntity.member.profession.name,
                                studyMemberJpaEntity.isAdmin
                        )
                )
                .from(studyMemberJpaEntity)
                .innerJoin(studyMemberJpaEntity.member)
                .where(studyMemberJpaEntity.study.id.eq(studyId))
                .fetch();
    }

    @Override
    public boolean isStudyJoinMember(Long studyId, Long memberId) {
        return query
                .selectFrom(studyMemberJpaEntity)
                .where(
                        studyMemberJpaEntity.study.id.eq(studyId),
                        studyMemberJpaEntity.member.id.eq(memberId)
                ).fetchOne() != null;
    }
}

package com.stumeet.server.studymember.adapter.out.persistence.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.stumeet.server.studymember.adapter.out.persistence.entity.StudyMemberJpaEntity;
import com.stumeet.server.studymember.application.port.in.response.QSimpleStudyMemberResponse;
import com.stumeet.server.studymember.application.port.in.response.SimpleStudyMemberResponse;
import com.stumeet.server.studymember.application.port.in.response.StudyMemberReviewStatusResponse;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static com.stumeet.server.studymember.adapter.out.persistence.entity.QStudyMemberJpaEntity.studyMemberJpaEntity;
import static com.stumeet.server.studymember.adapter.out.persistence.entity.QStudyMemberReview.studyMemberReview;

@RequiredArgsConstructor
public class JpaStudyMemberRepositoryCustomImpl implements JpaStudyMemberRepositoryCustom {

    private final JPAQueryFactory query;

    @Override
    public Optional<StudyMemberJpaEntity> findStudyMemberByStudyIdAndMemberId(Long studyId, Long memberId) {
        return query
            .select(studyMemberJpaEntity)
            .from(studyMemberJpaEntity)
            .innerJoin(studyMemberJpaEntity.member)
            .where(
                studyMemberJpaEntity.study.id.eq(studyId),
                studyMemberJpaEntity.member.id.eq(memberId)
            )
            .stream()
            .findFirst();
    }

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
    public List<StudyMemberReviewStatusResponse> findStudyMemberReviewStatusByMember(Long studyId, Long memberId) {
        return query
                .select(Projections.constructor(
                        StudyMemberReviewStatusResponse.class,
                        studyMemberJpaEntity.id,
                        studyMemberJpaEntity.member.id,
                        studyMemberJpaEntity.member.name,
                        studyMemberJpaEntity.member.region,
                        studyMemberJpaEntity.member.profession.name,
                        studyMemberJpaEntity.member.image,
                        studyMemberReview.id.isNotNull()
                ))
                .from(studyMemberJpaEntity)
                .leftJoin(studyMemberReview)
                .on(studyMemberReview.reviewerId.eq(memberId)
                        .and(studyMemberReview.revieweeId.eq(studyMemberJpaEntity.member.id)))
                .where(studyMemberJpaEntity.study.id.eq(studyId)
                        .and(studyMemberJpaEntity.member.id.ne(memberId)))
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

    @Override
    public boolean isAdmin(Long studyId, Long adminId) {
        return Boolean.TRUE.equals(
                query
                        .select(studyMemberJpaEntity.isAdmin)
                        .from(studyMemberJpaEntity)
                        .where(
                                studyMemberJpaEntity.study.id.eq(studyId),
                                studyMemberJpaEntity.member.id.eq(adminId)
                        ).fetchOne()
        );
    }

    @Override
    public boolean isSentGrape(Long studyId, Long memberId) {
        return Boolean.TRUE.equals(
                query
                        .select(studyMemberJpaEntity.isSentGrape)
                        .from(studyMemberJpaEntity)
                        .where(
                                studyMemberJpaEntity.study.id.eq(studyId),
                                studyMemberJpaEntity.member.id.eq(memberId)
                        ).fetchOne()
        );
    }
}

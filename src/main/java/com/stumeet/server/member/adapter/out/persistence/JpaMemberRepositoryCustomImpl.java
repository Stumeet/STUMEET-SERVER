package com.stumeet.server.member.adapter.out.persistence;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.stumeet.server.member.domain.OAuthProvider;
import lombok.RequiredArgsConstructor;

import static com.stumeet.server.member.adapter.out.persistence.QMemberJpaEntity.memberJpaEntity;
import static com.stumeet.server.member.adapter.out.persistence.QOAuthLoginJpaEntity.oAuthLoginJpaEntity;

@RequiredArgsConstructor
public class JpaMemberRepositoryCustomImpl implements JpaMemberRepositoryCustom {

    private final JPAQueryFactory query;

    @Override
    public MemberJpaEntity getByOAuthProviderId(String oAuthProviderId, OAuthProvider provider) {
        return query
                .select(memberJpaEntity)
                .from(memberJpaEntity)
                .join(oAuthLoginJpaEntity)
                .on(oAuthLoginJpaEntity.member.id.eq(memberJpaEntity.id))
                .where(
                        oAuthLoginJpaEntity.providerId.eq(oAuthProviderId),
                        oAuthLoginJpaEntity.providerName.eq(provider)
                )
                .fetchOne();
    }
}

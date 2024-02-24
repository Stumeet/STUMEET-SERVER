package com.stumeet.server.member.adapter.out.persistence;

import com.stumeet.server.common.annotation.PersistenceAdapter;
import com.stumeet.server.member.application.port.out.MemberCommandPort;
import com.stumeet.server.member.application.port.out.MemberQueryPort;
import com.stumeet.server.member.domain.Member;
import com.stumeet.server.member.domain.OAuthProvider;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class MemberPersistenceAdapter implements MemberQueryPort, MemberCommandPort {

    private final JpaMemberRepository jpaMemberRepository;
    private final MemberPersistenceMapper memberMapper;


    @Override
    public Member save(Member member) {
        MemberJpaEntity entity = jpaMemberRepository.save(memberMapper.toEntity(member));
        return memberMapper.toDomain(entity);
    }

    @Override
    public Member getByOAuthProviderId(String oAuthProviderId, OAuthProvider provider) {
        return memberMapper.toDomain(
                jpaMemberRepository.getByOAuthProviderId(oAuthProviderId, provider)
        );
    }

    @Override
    public Member getById(Long id) {
        MemberJpaEntity memberJpaEntity = jpaMemberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 ID의 유저가 존재하지 않습니다."));

        return memberMapper.toDomain(memberJpaEntity);
    }

    @Override
    public boolean isDuplicateNickname(String name) {
        return jpaMemberRepository.findByName(name)
                .isPresent();
    }
}

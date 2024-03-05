package com.stumeet.server.member.application.service;

import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.common.client.oauth.model.OAuthUserProfileResponse;
import com.stumeet.server.member.application.port.in.MemberOAuthUseCase;
import com.stumeet.server.member.application.port.out.MemberCommandPort;
import com.stumeet.server.member.application.port.out.MemberQueryPort;
import com.stumeet.server.member.application.port.out.OAuthLoginCommandPort;
import com.stumeet.server.member.application.port.out.OAuthLoginQueryPort;
import com.stumeet.server.member.domain.*;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class MemberOAuthService implements MemberOAuthUseCase {

    private final MemberCommandPort memberCommandPort;
    private final MemberQueryPort memberQueryPort;
    private final OAuthLoginQueryPort oAuthLoginQueryPort;
    private final OAuthLoginCommandPort oAuthLoginCommandPort;

    @Override
    public Member getMemberOrCreate(OAuthUserProfileResponse response, String provider) {
        boolean isRegisterUser = oAuthLoginQueryPort.existsByProviderId(response.id());
        OAuthProvider oAuthProvider = OAuthProvider.findByProvider(provider);
        Member member;

        if (isRegisterUser) {
            member = memberQueryPort.getByOAuthProviderId(response.id(), oAuthProvider);
        } else {
            MemberLevel initialLevel = MemberLevel.builder()
                    .rank(MemberRank.SEED)
                    .experience(0.0)
                    .build();
            member = memberCommandPort.save(
                    Member.builder()
                            .level(initialLevel)
                            .authType(AuthType.OAUTH)
                            .role(UserRole.FIRST_LOGIN)
                            .build()
            );
            oAuthLoginCommandPort.save(
                    OAuthLogin.builder()
                            .member(member)
                            .providerName(oAuthProvider)
                            .providerId(response.id())
                            .build()
            );
        }
        return member;
    }
}

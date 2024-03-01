package com.stumeet.server.helper;

import com.stumeet.server.common.auth.model.LoginMember;
import com.stumeet.server.common.auth.token.StumeetAuthenticationToken;
import com.stumeet.server.member.domain.Member;
import com.stumeet.server.member.domain.OAuthProvider;
import com.stumeet.server.stub.MemberStub;
import com.stumeet.server.stub.TokenStub;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

public class WithMockMemberSecurityContextFactory implements WithSecurityContextFactory<WithMockMember> {

    @Override
    public SecurityContext createSecurityContext(WithMockMember annotation) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        Member member = MemberStub.getMember(annotation);
        LoginMember loginMember = new LoginMember(member);
        Authentication token = StumeetAuthenticationToken.authenticateOAuth(
                loginMember.getAuthorities(),
                TokenStub.getMockAccessToken(),
                "refreshToken",
                OAuthProvider.KAKAO.getProvider(),
                loginMember
        );
        context.setAuthentication(token);
        return context;
    }
}

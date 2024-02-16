package com.stumeet.server.member.application.port.out;

import com.stumeet.server.member.domain.Member;
import com.stumeet.server.member.domain.OAuthProvider;

public interface MemberQueryPort {
    Member getByOAuthProviderId(String oAuthProviderId, OAuthProvider provider);

    Member getById(Long id);

}

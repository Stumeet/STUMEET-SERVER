package com.stumeet.server.member.application.port.in;

import com.stumeet.server.common.client.oauth.model.OAuthUserProfileResponse;
import com.stumeet.server.member.domain.Member;

public interface MemberOAuthUseCase {
    Member getMemberOrCreate(OAuthUserProfileResponse response, String provider);
}

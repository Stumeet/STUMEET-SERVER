package com.stumeet.server.member.application.port.out;

import com.stumeet.server.member.domain.OAuthLogin;

public interface OAuthLoginCommandPort {
    OAuthLogin save(OAuthLogin oAuthLogin);
}

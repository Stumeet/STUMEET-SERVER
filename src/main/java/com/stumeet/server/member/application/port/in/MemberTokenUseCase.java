package com.stumeet.server.member.application.port.in;

import com.stumeet.server.member.adapter.in.web.response.TokenResponse;
import com.stumeet.server.member.application.port.in.command.TokenRenewCommand;

public interface MemberTokenUseCase {



    TokenResponse renewAccessToken(TokenRenewCommand request);
}

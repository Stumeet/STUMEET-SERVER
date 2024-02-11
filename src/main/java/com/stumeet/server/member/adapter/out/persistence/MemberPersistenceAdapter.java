package com.stumeet.server.member.adapter.out.persistence;

import com.stumeet.server.member.application.port.out.MemberOAuthPort;
import com.stumeet.server.common.annotation.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class MemberPersistenceAdapter implements MemberOAuthPort {

    private final JpaMemberRepository jpaMemberRepository;

}

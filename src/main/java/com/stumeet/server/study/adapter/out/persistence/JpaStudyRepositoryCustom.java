package com.stumeet.server.study.adapter.out.persistence;

import com.stumeet.server.member.adapter.out.persistence.MemberJpaEntity;

public interface JpaStudyRepositoryCustom {
	MemberJpaEntity loadById(long id);
}

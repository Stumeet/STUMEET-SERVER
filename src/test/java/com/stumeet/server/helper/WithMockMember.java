package com.stumeet.server.helper;

import com.stumeet.server.member.domain.UserRole;
import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithMockMemberSecurityContextFactory.class)
public @interface WithMockMember {
    long id() default 1L;
    UserRole authority() default UserRole.MEMBER;

}

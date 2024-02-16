package com.stumeet.server.common.auth.token;

import com.stumeet.server.common.auth.model.LoginMember;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class StumeetAuthenticationToken extends AbstractAuthenticationToken {
    private final LoginMember principal;
    private final String accessToken;
    private final String provider;

    private StumeetAuthenticationToken(Collection<? extends GrantedAuthority> authorities, String accessToken, String provider, LoginMember principal, boolean isAuthenticated) {
        super(authorities);
        this.principal = principal;
        this.accessToken = accessToken;
        this.provider = provider;
        super.setAuthenticated(isAuthenticated);
    }

    public static StumeetAuthenticationToken createUnAuthenticationToken(String accessToken, String provider) {
        return new StumeetAuthenticationToken(List.of(), accessToken, provider, null, false);
    }

    public static StumeetAuthenticationToken createAuthenticationOAuthToken(Collection<? extends GrantedAuthority> authorities, String accessToken, String provider, LoginMember principal) {
        return new StumeetAuthenticationToken(authorities, accessToken, provider, principal, true);
    }

    public static StumeetAuthenticationToken createAuthenticationJwtToken(Collection<? extends GrantedAuthority> authorities, String accessToken, LoginMember principal) {
        return new StumeetAuthenticationToken(authorities, accessToken, null, principal, true);
    }

    @Override
    public String getCredentials() {
        return accessToken;
    }

    @Override
    public LoginMember getPrincipal() {
        return principal;
    }

    public String getProvider() {
        return provider;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        StumeetAuthenticationToken that = (StumeetAuthenticationToken) o;
        return Objects.equals(principal, that.principal) && Objects.equals(accessToken, that.accessToken) && Objects.equals(provider, that.provider);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), principal, accessToken, provider);
    }
}

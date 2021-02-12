package by.itacademy.security.model.authentication;

import by.itacademy.security.model.CredentialsContainer;
import by.itacademy.security.model.GrantedAuthority;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.util.Collection;


@AllArgsConstructor
public class LoginPasswordAuthenticationToken implements Authentication, CredentialsContainer {

    private Object credentials;
    @Setter
    private Object principal;
    private boolean authenticated;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) {
        authenticated = isAuthenticated;
    }

    @Override
    public void eraseCredentials() {
        credentials = null;
    }
}

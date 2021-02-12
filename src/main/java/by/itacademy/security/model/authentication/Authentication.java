package by.itacademy.security.model.authentication;

import by.itacademy.security.model.GrantedAuthority;

import java.util.Collection;

public interface Authentication {

    Collection<? extends GrantedAuthority> getAuthorities();

    Object getCredentials();

    //Object getDetails();

    Object getPrincipal();

    boolean	isAuthenticated();

    void setAuthenticated(boolean isAuthenticated);

}

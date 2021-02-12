package by.itacademy.security.model;

import java.util.Collection;

public interface UserDetails {

    Collection<? extends GrantedAuthority> getAuthorities();

    String	getPassword();

    String	getUsername();

    boolean	isAccountNonLocked();
}

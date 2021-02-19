package by.itacademy.model.security.user;

import java.util.Collection;

public interface UserDetails {

    Collection<? extends GrantedAuthority> getAuthorities();

    String	getPassword();

    String getLogin();

    boolean	isAccountNonLocked();
}

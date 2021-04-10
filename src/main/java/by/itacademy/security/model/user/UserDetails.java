package by.itacademy.security.model.user;

import java.util.Collection;

public interface UserDetails {

    int getId();

    Collection<? extends GrantedAuthority> getAuthorities();

    String	getPassword();

    String getLogin();

    boolean	isAccountNonLocked();
}

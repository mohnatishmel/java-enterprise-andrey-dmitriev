package by.itacademy.model.user;

import by.itacademy.security.CredentialsContainer;
import by.itacademy.security.GrantedAuthority;
import by.itacademy.security.UserDetails;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.Collection;

@Data
@Builder

public class User implements UserDetails, CredentialsContainer {

    @Getter
    private final int id;
    private LoginPasswordCredentials credentials;
    private final PersonalInformation personalInformation;
    private final Collection<Role> roles;
    private final boolean accountNotLocked;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
       return credentials.getPassword();
    }

    @Override
    public String getUsername() {
        return credentials.getLogin();
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNotLocked;
    }

    @Override
    public void eraseCredentials() {
        credentials = null;
    }

    public String getFirstname() {
        return personalInformation.getFirstname();
    }

    public String getLastname() {
        return personalInformation.getFirstname();
    }
}

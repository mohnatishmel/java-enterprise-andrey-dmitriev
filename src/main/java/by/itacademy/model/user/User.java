package by.itacademy.model.user;

import by.itacademy.security.model.GrantedAuthority;
import by.itacademy.security.model.UserDetails;
import lombok.*;

import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class User implements UserDetails, CredentialsContainer {

    @Getter
    private int id;
    private Credential credential;
    private PersonalInformation personalInformation;
    private Collection<Role> roles;
    private boolean accountNotLocked;

    public static User.UserBuilder builder() {
        return new User.UserBuilder();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
       return credential.getPassword();
    }

    @Override
    public String getUsername() {
        return credential.getLogin();
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNotLocked;
    }

    @Override
    public void eraseCredentials() {
        credential = null;
    }

}

package by.itacademy.model.user;

import by.itacademy.model.security.user.CredentialsContainer;
import by.itacademy.model.security.user.GrantedAuthority;
import by.itacademy.model.security.user.UserDetails;
import by.itacademy.model.task.Task;
import lombok.*;

import java.util.Collection;
import java.util.List;

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
    public String getLogin() {
        return credential.getLogin();
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNotLocked;
    }

    @Override
    public void eraseCredentials() {
        credential.setPassword(null);
    }

}

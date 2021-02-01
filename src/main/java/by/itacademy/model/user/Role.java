package by.itacademy.model.user;

import by.itacademy.security.GrantedAuthority;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Role implements GrantedAuthority {

    private final String role;

    @Override
    public String getAuthority() {
        return null;
    }
}

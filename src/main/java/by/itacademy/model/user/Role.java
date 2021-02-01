package by.itacademy.model.user;

import by.itacademy.security.GrantedAuthority;
import lombok.Data;

@Data
public class Role implements GrantedAuthority {

    private final int id;
    private final String role;

    @Override
    public String getAuthority() {
        return role;
    }
}

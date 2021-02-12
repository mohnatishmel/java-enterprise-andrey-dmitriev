package by.itacademy.model.user;

import by.itacademy.security.model.GrantedAuthority;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Role implements GrantedAuthority {

    public Role(String role) {
        this.role = role;
    }

    @Getter
    private int id;
    private String role;

    @Override
    public String getAuthority() {
        return role;
    }
}

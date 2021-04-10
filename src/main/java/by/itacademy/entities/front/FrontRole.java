package by.itacademy.entities.front;

import by.itacademy.security.model.user.GrantedAuthority;
import lombok.*;

@AllArgsConstructor


public class FrontRole implements GrantedAuthority {

    private String role;

    @Override
    public String getAuthority() {
        return role;
    }

}

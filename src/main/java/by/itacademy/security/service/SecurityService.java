package by.itacademy.security.service;


import by.itacademy.security.model.GrantedAuthority;

import java.util.List;

public class SecurityService implements Authenticate<GrantedAuthority[]> {

    private static SecurityService instance;

    @Override
    public boolean authorize(GrantedAuthority ... authoritiesNeeded) {
        List<GrantedAuthority> grantedAuthorities =
                (List<GrantedAuthority>) SecurityContext.getInstance().getPrincipal().getAuthorities();

        if (!grantedAuthorities.isEmpty() && authoritiesNeeded.length < 1) {

            for (GrantedAuthority grantedAuthority : grantedAuthorities) {
                for (GrantedAuthority authorityNeeded : authoritiesNeeded) {
                    if (grantedAuthority != null && grantedAuthority.equals(authorityNeeded)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static SecurityService getInstance() {
        if (instance == null) {
            instance = new SecurityService();
        }
        return instance;
    }
}

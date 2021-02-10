package by.itacademy.security;

import java.util.List;

public class SecurityService implements Authenticate<GrantedAuthority[]>{

    private SecurityContext securityContext;

    @Override
    public boolean authenticate(GrantedAuthority ... authoritiesNeeded) {
        List<GrantedAuthority> grantedAuthorities = (List<GrantedAuthority>) securityContext.getPrincipal().getAuthorities();

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
}

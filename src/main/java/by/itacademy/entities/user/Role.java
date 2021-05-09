package by.itacademy.entities.user;

import by.itacademy.security.model.user.GrantedAuthority;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@EqualsAndHashCode(exclude = "users")
@ToString(exclude = "users")

@Entity
@Table(name = "ROLES")
public class Role implements GrantedAuthority {

    public Role(String roleName) {
        this.roleName = roleName;
    }

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROLE_ID")
    private int id;
    @Column(name = "ROLE")
    private String roleName;
    @ManyToMany(fetch = FetchType.LAZY,
            mappedBy = "roles",
            cascade = {CascadeType.MERGE})
    private Set<User> users;

//    {
//        users = new HashSet<>();
//    }

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    @Override
    public String getAuthority() {
        return roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role1 = (Role) o;

        return roleName != null ? roleName.equals(role1.roleName) : role1.roleName == null;
    }

    @Override
    public int hashCode() {
        return roleName != null ? roleName.hashCode() : 0;
    }
}

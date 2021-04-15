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

    public Role(String role) {
        this.role = role;
    }

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROLE_ID")
    private int id;
    private String role;
    @ManyToMany(mappedBy = "roles",
            cascade = {CascadeType.MERGE})
    private Set<User> users;

    public Role(int id, String role) {
        this.id = id;
        this.role = role;
    }

    {
        users = new HashSet<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    @Override
    public String getAuthority() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role1 = (Role) o;

        return role != null ? role.equals(role1.role) : role1.role == null;
    }

    @Override
    public int hashCode() {
        return role != null ? role.hashCode() : 0;
    }
}

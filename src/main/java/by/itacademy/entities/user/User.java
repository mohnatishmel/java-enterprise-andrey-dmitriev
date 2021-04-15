package by.itacademy.entities.user;

import by.itacademy.security.model.user.CredentialsContainer;
import by.itacademy.security.model.user.GrantedAuthority;
import by.itacademy.security.model.user.UserDetails;
import by.itacademy.entities.task.Task;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode(exclude = {"roles", "tasks"})
@ToString(exclude = {"roles", "tasks"})

@Entity
@Table(name = "USERS")
public class User implements UserDetails, CredentialsContainer {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private int id;

    @OneToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "credentials_id", referencedColumnName = "credentials_id")
    private  Credential credential;

    @OneToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "personal_information_id", referencedColumnName = "personal_information_id")
    private PersonalInformation personalInformation;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "roles_map",
            joinColumns =@JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"))
    private Collection<Role> roles;
    @Column(name = "PROFILE_ENABLE")
    private boolean accountNotLocked;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Task> tasks;

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
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

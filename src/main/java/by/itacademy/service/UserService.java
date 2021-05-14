package by.itacademy.service;

import by.itacademy.entities.user.Credential;
import by.itacademy.entities.user.Role;
import by.itacademy.exception.ApplicationBasedException;
import by.itacademy.entities.user.PersonalInformation;
import by.itacademy.entities.user.User;
import by.itacademy.exception.security.authentication.AuthenticationException;
import by.itacademy.exception.security.authentication.UserNameAlreadyExistsException;
import by.itacademy.exception.security.authentication.UserNotFoundException;
import by.itacademy.exception.security.authorization.AuthorizationException;
import by.itacademy.persistence.*;
import by.itacademy.security.encoder.PasswordEncoder;
import by.itacademy.security.model.authentication.AuthenticationToken;
import by.itacademy.security.model.authentication.UserDetailService;
import by.itacademy.security.model.user.GrantedAuthority;
import by.itacademy.security.model.user.Roles;
import by.itacademy.security.model.user.UserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@RequiredArgsConstructor

@Service
public class UserService implements UserDetailService {

    private final UserDao userDao;
    private final PersonalInformationDao personalInformationDao;
    private final CredentialsDao credentialsDao;
    private final RoleDao roleDao;
    private final TaskService taskService;
    private final UnlockRequestMessageService unlockRequestMessageService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails getByName(String name) throws UserNotFoundException {
        return userDao.getByName(name).orElseThrow(() ->
                new UserNotFoundException(String.format("User with name '%s' not found", name)));
    }

    public User getById(int id) throws ApplicationBasedException {
        try {
            return userDao.getById(id);
        } catch (DataAccessException e) {
            throw new ApplicationBasedException(e);
        }
    }

    public List<User> getAllLocked(int excludeId) throws ApplicationBasedException {
        try {
            return userDao.getByAccountNonLocked(false, excludeId);
        } catch (DataAccessException e) {
            throw new ApplicationBasedException(e);
        }
    }

    public List<User> getAllNotLocked(int excludeId) throws ApplicationBasedException {
        try {
            return userDao.getByAccountNonLocked(true, excludeId);
        } catch (DataAccessException e) {
            throw new ApplicationBasedException(e);
        }
    }

    public User registerUser(AuthenticationToken token) throws AuthenticationException {
        User user;
        if ( ! userDao.getByName(token.getLogin()).isPresent()) {
            user = initUser(token);
            List<Role> roles = new ArrayList<>();
            for (GrantedAuthority role : user.getAuthorities()) {
                role =  roleDao.getRoleByRoleName(role.getAuthority());
                role.getAuthority();
                ((Role)role).addUser(user);
                roles.add((Role) role);
            }
            user.setRoles(roles);
            user = userDao.save(user);
        } else {
            throw new UserNameAlreadyExistsException("User with such name already exist");
        }
        return user;
    }

    public void updateUser(User updateUser) throws ApplicationBasedException {
        try {
            User user = userDao.getById(updateUser.getId());

            PersonalInformation pi = updateUser.getPersonalInformation();
            boolean accountNotLocked = updateUser.isAccountNotLocked();

            if (pi != null) {
                user.setPersonalInformation(pi);
            }
            user.setAccountNotLocked(accountNotLocked);

            userDao.save(user);
        } catch (DataAccessException e) {
            throw new ApplicationBasedException(e);
        }
    }

    public void deleteUser(User user) throws ApplicationBasedException {
        try {
            int userId = user.getId();
            user = userDao.getById(userId);
            taskService.deleteByUserId(userId);
            unlockRequestMessageService.deleteByUserId(userId);
            roleDao.deleteByUserId(userId);
            userDao.delete(userId);
            personalInformationDao.deleteById(user.getPersonalInformation().getId());
            credentialsDao.deleteById(user.getCredential().getId());

        } catch (DataAccessException e) {
            throw new ApplicationBasedException(e);
        }
    }

    private User initUser(AuthenticationToken token) {
        List<Role> roles = new ArrayList<>();
        roles.add(new Role(Roles.USER));

        return User.builder()
                .credential(new Credential(token.getLogin(), passwordEncoder.encodePassword(token.getPassword())))
                .personalInformation(new PersonalInformation(0, "", ""))
                .roles(roles)
                .accountNotLocked(true)
                .build();
    }
}

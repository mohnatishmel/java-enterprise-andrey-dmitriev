package by.itacademy.controller;

import by.itacademy.entities.front.FrontUser;;
import by.itacademy.entities.front.Message;
import by.itacademy.entities.user.User;
import by.itacademy.exception.ApplicationBasedException;
import by.itacademy.exception.security.authorization.AuthorizationException;
import by.itacademy.controller.converter.Converter;
import by.itacademy.service.FacadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor

@RestController
@RequestMapping("/rest/users")
public class UserRestController {

    private final FacadeService facadeService;
    private final Converter<List<User>, List<FrontUser>> userListConverter;

    @GetMapping("/locked")
    public ResponseEntity<List<User>> findAllLocked() throws ApplicationBasedException, AuthorizationException {
        List<User> userList = facadeService.getAllLocked();
        return new ResponseEntity(userListConverter.convert(userList), HttpStatus.OK);
    }

    @PutMapping("/locked")
    public ResponseEntity<Message> updateNotLocked(@RequestBody User user) throws ApplicationBasedException, AuthorizationException {
        facadeService.updateUserIsLocked(user);
        Message message = new Message("User successfully updated");
        return new ResponseEntity(message, HttpStatus.OK);
    }

    @GetMapping("/notLocked")
    public ResponseEntity<List<User>> findAllNotLocked() throws ApplicationBasedException, AuthorizationException {
        List<User> userList = facadeService.getAllNotLocked();
        return new ResponseEntity(userListConverter.convert(userList), HttpStatus.OK);
    }

    @DeleteMapping("/")
    public ResponseEntity<Message> delete(@RequestBody User user) throws ApplicationBasedException, AuthorizationException {
        facadeService.deleteUser(user);
        Message message = new Message("User deleted");
        return new ResponseEntity(message, HttpStatus.OK);
    }
}

package by.itacademy.controller;

import by.itacademy.entities.front.Message;
import by.itacademy.entities.message.UnlockRequestMessage;
import by.itacademy.exception.ApplicationBasedException;
import by.itacademy.exception.security.authorization.AuthorizationException;
import by.itacademy.service.FacadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor

@RestController
@RequestMapping("/rest/unlock/requests/")
public class UnlockRequestMessageRestController {

    private final FacadeService facadeService;

    @GetMapping("messages")
    public  ResponseEntity<List<UnlockRequestMessage>> findAll()
            throws ApplicationBasedException, AuthorizationException {
        return new ResponseEntity(facadeService.getUnlockRequestMessages(), HttpStatus.OK);
    }

    @PostMapping("messages")
    public ResponseEntity<Message> create(@RequestBody UnlockRequestMessage unlockRequest)
            throws ApplicationBasedException, AuthorizationException {
        facadeService.createUnlockUserRequest(unlockRequest);
        return new ResponseEntity(new Message("Unlock request was created"), HttpStatus.OK);
    }

    @PutMapping("messages")
    public  ResponseEntity<Message> resolve(@RequestBody UnlockRequestMessage unlockRequest)
            throws ApplicationBasedException, AuthorizationException {
        facadeService.resolveUnlockUserRequest(unlockRequest);
        return new ResponseEntity(new Message("Request was successfully resolved"), HttpStatus.OK);
    }

    @DeleteMapping("messages")
    public  ResponseEntity<Message> delete(@RequestBody UnlockRequestMessage unlockRequest)
            throws ApplicationBasedException, AuthorizationException {
        facadeService.deleteUnlockUserRequest(unlockRequest);
        return new ResponseEntity(new Message("Request was successfully deleted"), HttpStatus.OK);
    }
}

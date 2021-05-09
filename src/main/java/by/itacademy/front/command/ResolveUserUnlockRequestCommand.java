//package by.itacademy.front.command;
//
//import by.itacademy.exception.ApplicationBasedException;
//import by.itacademy.exception.security.authorization.AuthorizationException;
//import by.itacademy.front.mapper.impl.JsonToJavaUnlockRequestMessageMapper;
//import by.itacademy.entities.message.UnlockRequestMessage;
//import by.itacademy.service.FacadeService;
//import com.google.gson.Gson;
//import org.springframework.stereotype.Controller;
//
//import javax.servlet.ServletException;
//import java.io.IOException;
//public class ResolveUserUnlockRequestCommand extends FrontCommand{
//
//    @Override
//    public void process() throws ServletException, IOException, ApplicationBasedException, AuthorizationException {
//        UnlockRequestMessage unlockMessage = new JsonToJavaUnlockRequestMessageMapper().map(request);
//
//        facadeService.resolveUnlockUserRequest(unlockMessage);
//
//        String messageBody = "Request was successfully resolved";
//        Message message = new Message(messageBody);
//        String json = new Gson().toJson(message);
//        returnResponse(json);
//    }
//}

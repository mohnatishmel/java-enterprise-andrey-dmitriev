package by.itacademy.front.command;

import by.itacademy.exception.ApplicationBasedException;
import by.itacademy.exception.security.authorization.AuthorizationException;
import by.itacademy.service.FacadeService;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Log4j2
public class DeleteTaskCommand extends FrontCommand {

    @Override
    public void process() throws ServletException, IOException, ApplicationBasedException, AuthorizationException {

        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));

        String json;
        int id = 0;

            json = br.readLine();
            JsonParser parser = new JsonParser();
            JsonElement jsonTree = parser.parse(json);
            if (jsonTree.isJsonObject()) {
                JsonObject jsonObject = jsonTree.getAsJsonObject();
                jsonObject = jsonObject.getAsJsonObject("task");

                id = jsonObject.get("id").getAsInt();
        }

        facadeService.deleteTask(id);

        returnMessage(String.format("Task_%s successfully deleted", id), 200);
    }
}

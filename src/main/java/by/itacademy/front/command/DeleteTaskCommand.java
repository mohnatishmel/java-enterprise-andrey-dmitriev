package by.itacademy.front.command;

import by.itacademy.exception.ApplicationBasedException;
import by.itacademy.service.Service;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Log4j2

public class DeleteTaskCommand extends FrontCommand {

    private Service service;

    {
        service = Service.getInstance();
    }

    @Override
    public void process() throws ServletException, IOException, ApplicationBasedException {

        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));

        String json = "";
        int id = 0;

        if (br != null) {

            json = br.readLine();
            JsonParser parser = new JsonParser();
            JsonElement jsonTree = parser.parse(json);
            if (jsonTree.isJsonObject()) {
                JsonObject jsonObject = jsonTree.getAsJsonObject();
                jsonObject = jsonObject.getAsJsonObject("task");

                id = jsonObject.get("id").getAsInt();
            }
        }

        service.deleteTask(id);

        FrontCommand command = new LoadTaskListCommand();
        command.init(context, request, response);
        command.process();
    }
}

package by.itacademy.front.command;


import by.itacademy.model.task.Task;
import by.itacademy.service.Service;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;


@Log4j2

public class CreateTaskCommand extends FrontCommand {

    private Service service;

    {
        service = Service.getInstance();
    }

    @Override
    public void process() throws ServletException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));

        String json = "";

        if(br != null){

            json = br.readLine();
            JsonParser parser = new JsonParser();
            JsonElement jsonTree = parser.parse(json);
            if(jsonTree.isJsonObject()){
                JsonObject jsonObject = jsonTree.getAsJsonObject();
                jsonObject = jsonObject.getAsJsonObject("task");

                String name = jsonObject.get("name").getAsString();
                String description = jsonObject.get("description").getAsString();
                long milliseconds = jsonObject.get("deadLine").getAsLong();
                Date deadline = new Date(milliseconds);

                Task task = Task.builder()
                        .name(name)
                        .description(description)
                        .deaLine(deadline)
                        .build();
                service.createTask(task);
            }
        }

        FrontCommand command = new LoadTaskListCommand();
        command.init(context, request, response);
        command.process();
    }
}

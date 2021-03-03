package by.itacademy.front.command.mapper;

import by.itacademy.model.task.Task;
import by.itacademy.service.Service;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;

public class JsonToJavaTaskMapper {

    public static Task map(BufferedReader br) throws IOException {
        String json = "";
        Task task = null;

        if (br != null) {

            json = br.readLine();
            JsonParser parser = new JsonParser();
            JsonElement jsonTree = parser.parse(json);
            if (jsonTree.isJsonObject()) {
                JsonObject jsonObject = jsonTree.getAsJsonObject();
                jsonObject = jsonObject.getAsJsonObject("task");

                int id = jsonObject.get("id").getAsInt();
                String name = jsonObject.get("name").getAsString();
                String description = jsonObject.get("description").getAsString();
                long milliseconds = jsonObject.get("deadLine").getAsLong();
                boolean fixed = jsonObject.get("fixed").getAsBoolean();
                boolean inBasket = jsonObject.get("inBasket").getAsBoolean();
                Date deadline = new Date(milliseconds);

                task = Task.builder()
                        .id(id)
                        .name(name)
                        .description(description)
                        .deadLine(deadline)
                        .fixed(fixed)
                        .inBasket(inBasket)
                        .build();
            }
        }
        return task;
    }
}

package by.itacademy.front.mapper.impl;

import by.itacademy.front.mapper.Mapper;
import by.itacademy.model.message.UnlockRequestMessage;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JsonToJavaUnlockRequestMessageMapper  implements Mapper<UnlockRequestMessage> {

    public UnlockRequestMessage map(HttpServletRequest request) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));

        String json;
        UnlockRequestMessage message = null;

            json = br.readLine();
            JsonParser parser = new JsonParser();
            JsonElement jsonTree = parser.parse(json);
            if (jsonTree.isJsonObject()) {
                JsonObject jsonObject = jsonTree.getAsJsonObject();
                jsonObject = jsonObject.getAsJsonObject("message");

                int id = jsonObject.get("id").getAsInt();
                int userId = jsonObject.get("userId").getAsInt();
                String userName = jsonObject.get("userName").getAsString();
                String messageBody = jsonObject.get("messageBody").getAsString();

                message = UnlockRequestMessage.builder()
                        .id(id)
                        .userId(userId)
                        .userName(userName)
                        .messageBody(messageBody)
                        .build();
        }
        return message;
    }
}

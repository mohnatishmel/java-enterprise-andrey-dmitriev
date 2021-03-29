package by.itacademy.front.command.mapper;

import by.itacademy.model.user.Credential;
import by.itacademy.model.user.User;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JsonToJavaUserMapper {

    public static User map(HttpServletRequest request) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));

        String json = "";
        User user = null;

        if (br != null) {

            json = br.readLine();
            JsonParser parser = new JsonParser();
            JsonElement jsonTree = parser.parse(json);
            if (jsonTree.isJsonObject()) {
                JsonObject jsonObject = jsonTree.getAsJsonObject();
                jsonObject = jsonObject.getAsJsonObject("user");

                int id = jsonObject.get("id").getAsInt();
                boolean accountNotLocked = jsonObject.get("accountNotLocked").getAsBoolean();

                user = User.builder()
                        .id(id)
                        .accountNotLocked(accountNotLocked)
                        .build();
            }
        }
        return user;
    }
}

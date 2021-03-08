package by.itacademy.front.command.mapper;

import by.itacademy.model.security.authentication.AuthenticationToken;
import by.itacademy.model.task.Task;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

public class JsonToJavaAuthenticateTokenMapper {

    public static AuthenticationToken map(HttpServletRequest request) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));

        String json = "";
        AuthenticationToken token = null;

        if (br != null) {

            json = br.readLine();
            JsonParser parser = new JsonParser();
            JsonElement jsonTree = parser.parse(json);
            if (jsonTree.isJsonObject()) {
                JsonObject jsonObject = jsonTree.getAsJsonObject();
                jsonObject = jsonObject.getAsJsonObject("token");

                String login = jsonObject.get("login").getAsString();
                String pass = jsonObject.get("password").getAsString();

                token = new AuthenticationToken(login, pass);
            }
        }
        return token;
    }
}

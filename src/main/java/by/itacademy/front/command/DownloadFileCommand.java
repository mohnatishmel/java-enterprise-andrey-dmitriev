package by.itacademy.front.command;

import by.itacademy.exception.ApplicationBasedException;
import by.itacademy.exception.security.authorization.AuthorizationException;
import by.itacademy.model.file.File;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Base64;

@Log4j2

public class DownloadFileCommand extends FrontCommand {

    @Override
    public void process() throws ServletException, IOException, ApplicationBasedException, AuthorizationException {
        long id = Long.parseLong(request.getParameter("id"));

        File file = facadeService.getFile(id);
        byte[] bytes = file.getBytes();

        String base64 = Base64.getEncoder().encodeToString(bytes);

        String json = new Gson().toJson(new JsonFile(file.getName(), base64));

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    @AllArgsConstructor
    private class JsonFile {
        private final String name;
        private final String base64;
    }
}

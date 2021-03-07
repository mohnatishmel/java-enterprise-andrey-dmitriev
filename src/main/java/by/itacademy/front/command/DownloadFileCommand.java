package by.itacademy.front.command;

import by.itacademy.exception.ApplicationBasedException;
import by.itacademy.model.file.File;
import by.itacademy.service.Service;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Base64;

@Log4j2

public class DownloadFileCommand extends FrontCommand {

    private Service service;

    {
        service = Service.getInstance();
    }

    @Override
    public void process() throws ServletException, IOException, ApplicationBasedException {
        long id = Long.parseLong(request.getParameter("id"));

        File file = service.getFile(id);
        byte[] bytes = file.getBytes();
//        response.setContentLength(bytes.length);
//        OutputStream os = response.getOutputStream();
//
//        os.write(bytes , 0, bytes.length);
//        os.close();

        String base64 = Base64.getEncoder().encodeToString(bytes);

        String json = new Gson().toJson(new JsonFile(file.getName(), base64));

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    @AllArgsConstructor
    private class JsonFile {
        private String name;
        private String base64;
    }
}

package by.itacademy.front.command;

import by.itacademy.exception.ApplicationBasedException;
import by.itacademy.exception.security.authorization.AuthorizationException;
import by.itacademy.entities.file.File;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.Part;
import java.io.*;

@Log4j2

public class UploadFileCommand extends FrontCommand {

    @Override
    public void process() throws ServletException, IOException, ApplicationBasedException, AuthorizationException {
        Part partFile = request.getPart("file");
        Part partFileName = request.getPart("name");
        InputStream isFile = partFile.getInputStream();

        InputStream isFileName = partFileName.getInputStream();
        InputStreamReader isReader = new InputStreamReader(isFileName);
        BufferedReader reader = new BufferedReader(isReader);
        StringBuilder sb = new StringBuilder();
        String str;

        while((str = reader.readLine())!= null){
            sb.append(str);
        }

        String fileName = sb.toString();
        byte[] bytes = isFile.readAllBytes();

        long id = Long.parseLong(request.getParameter("id"));

        File file = new File(id, bytes, fileName);

        facadeService.uploadFileForTask(file);

        returnMessage(String.format("File_%s was successfully uploaded", file.getId()), 200);
    }
}

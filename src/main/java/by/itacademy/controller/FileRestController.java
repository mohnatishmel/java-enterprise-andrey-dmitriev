package by.itacademy.controller;

import by.itacademy.entities.file.File;
import by.itacademy.entities.front.FrontFile;
import by.itacademy.entities.front.FrontPage;
import by.itacademy.entities.front.FrontTask;
import by.itacademy.entities.task.Task;
import by.itacademy.exception.ApplicationBasedException;
import by.itacademy.exception.security.authorization.AuthorizationException;
import by.itacademy.front.converter.Converter;
import by.itacademy.front.converter.impl.FileToFrontFileConverter;
import by.itacademy.security.service.SecurityContext;
import by.itacademy.service.FacadeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Part;
import javax.websocket.server.PathParam;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@RequiredArgsConstructor

@RestController
@RequestMapping("/tasks/")
public class FileRestController {

    private final FacadeService facadeService;
    private final FileToFrontFileConverter converter;

    @GetMapping("{id}/files")
    public ResponseEntity<FrontFile> get(@PathVariable int id) throws ApplicationBasedException, AuthorizationException {
        File file = facadeService.getFile(id);
        return new ResponseEntity(converter.convert(file),HttpStatus.OK);
    }

    @PostMapping("{id}/files")
    public  ResponseEntity<Message> create(@RequestParam("file") MultipartFile multipartFile, @PathVariable int id)
            throws ApplicationBasedException, AuthorizationException, IOException {
        String fileName = multipartFile.getOriginalFilename();
        File file = new File(id, multipartFile.getBytes(), fileName);

        facadeService.uploadFileForTask(file);

        return new ResponseEntity(new Message(String.format("File_%s was successfully uploaded", file.getId())),HttpStatus.OK);
    }

//    private ResponseEntity<FrontPage<List<FrontTask>>> getTasksList(Page<Task> page) {
//        List<FrontTask> frontTasks = taskConverter.convert(page.getContent());
//        FrontPage<List<FrontTask>> frontPage = new FrontPage(frontTasks, page.getTotalElements());
//        return new ResponseEntity(frontPage, HttpStatus.OK);
//    }

    private class Message {

        public Message(String message) {
            this.message = message;
        }
        @Getter
        private String message;
    }
}

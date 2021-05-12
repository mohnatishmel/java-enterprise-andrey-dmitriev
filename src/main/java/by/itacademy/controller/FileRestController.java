package by.itacademy.controller;

import by.itacademy.entities.file.File;
import by.itacademy.entities.front.FrontFile;
import by.itacademy.exception.ApplicationBasedException;
import by.itacademy.exception.security.authorization.AuthorizationException;
import by.itacademy.controller.converter.impl.FileToFrontFileConverter;
import by.itacademy.service.FacadeService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor

@RestController
@RequestMapping("/rest/tasks/")
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

    private class Message {

        public Message(String message) {
            this.message = message;
        }
        @Getter
        private String message;
    }
}

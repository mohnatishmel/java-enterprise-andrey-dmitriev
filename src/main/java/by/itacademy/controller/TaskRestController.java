package by.itacademy.controller;

import by.itacademy.entities.front.FrontPage;
import by.itacademy.entities.front.FrontTask;
import by.itacademy.entities.front.Message;
import by.itacademy.entities.task.Task;
import by.itacademy.exception.ApplicationBasedException;
import by.itacademy.exception.security.authorization.AuthorizationException;
import by.itacademy.controller.converter.Converter;
import by.itacademy.security.service.SecurityContext;
import by.itacademy.service.FacadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor

@RestController
@RequestMapping("/rest/")
public class TaskRestController {

    private final FacadeService facadeService;
    private final SecurityContext securityContext;
    private final Converter<List<Task>, List<FrontTask>> taskConverter;

    @GetMapping("tasks/today")
    public ResponseEntity<FrontPage<List<FrontTask>>> getTodayTasks(@RequestParam int size, @RequestParam int pageNumber) 
            throws ApplicationBasedException, AuthorizationException {
        Pageable pageable = PageRequest.of(pageNumber, size);
        Page<Task> taskPage = facadeService.getTodayTasksForUser(getId(), pageable);
        return getTasksList(taskPage);
    }

    @GetMapping("tasks/tomorrow")
    public ResponseEntity<FrontPage<List<FrontTask>>> getTomorrowTasks(@RequestParam int size, @RequestParam int pageNumber)
            throws ApplicationBasedException, AuthorizationException {
        Pageable pageable = PageRequest.of(pageNumber, size);
        Page<Task> taskPage = facadeService.getTomorrowTasksForUser(getId(), pageable);
        return getTasksList(taskPage);
    }

    @GetMapping("tasks/someday")
    public ResponseEntity<FrontPage<List<FrontTask>>> getSomedayTasks(@RequestParam int size, @RequestParam int pageNumber)
            throws ApplicationBasedException, AuthorizationException {
        Pageable pageable = PageRequest.of(pageNumber, size);
        Page<Task> taskPage = facadeService.getSomedayTasksForUser(getId(), pageable);
        return getTasksList(taskPage);
    }

    @GetMapping("tasks/trashBox")
    public ResponseEntity<FrontPage<List<FrontTask>>> getTrashBoxTasks(@RequestParam int size, @RequestParam int pageNumber)
            throws ApplicationBasedException, AuthorizationException {
        Pageable pageable = PageRequest.of(pageNumber, size);
        Page<Task> taskPage = facadeService.getTrashBoxTasksForUser(getId(), pageable);
        return getTasksList(taskPage);
    }

    @GetMapping("tasks/fixed")
    public ResponseEntity<FrontPage<List<FrontTask>>> getFixedTasks(@RequestParam int size, @RequestParam int pageNumber)
            throws ApplicationBasedException, AuthorizationException {
        Pageable pageable = PageRequest.of(pageNumber, size);
        Page<Task> taskPage = facadeService.getFixedTasksForUser(getId(), pageable);
        return getTasksList(taskPage);
    }

    private int getId() {
        return securityContext.getPrincipal().getId();
    }

    @DeleteMapping("tasks")
    public ResponseEntity<Message> delete(@RequestBody Task task) throws ApplicationBasedException, AuthorizationException {
        int id = task.getId();
        facadeService.deleteTask(id);
        return new ResponseEntity(new Message("Task deleted"),HttpStatus.OK);
    }

    @PutMapping("tasks")
    public ResponseEntity<Message> update(@RequestBody Task task) throws ApplicationBasedException, AuthorizationException {
        facadeService.updateTask(task);
        return new ResponseEntity(new Message("Task updated"),HttpStatus.OK);
    }

    @PostMapping("tasks")
    public  ResponseEntity<Message> create(@RequestBody Task task) throws ApplicationBasedException, AuthorizationException {
        facadeService.createTask(task);
        return new ResponseEntity(new Message("Task created"),HttpStatus.OK);
    }

    private ResponseEntity<FrontPage<List<FrontTask>>> getTasksList(Page<Task> page) {
        List<FrontTask> frontTasks = taskConverter.convert(page.getContent());
        FrontPage<List<FrontTask>> frontPage = new FrontPage(page.getNumber(), frontTasks, page.getTotalElements());
        return new ResponseEntity(frontPage, HttpStatus.OK);
    }
}

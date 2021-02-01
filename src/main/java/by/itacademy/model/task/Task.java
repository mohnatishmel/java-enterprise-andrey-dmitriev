package by.itacademy.model.task;

import by.itacademy.model.user.User;
import lombok.*;

import java.util.Collection;
import java.util.Date;

@Data
@Builder
public class Task {

    private int id;
    private int userId;
    private TaskInformation taskInfo;
    private Date deaLine;
    private boolean fixed;
    private boolean inBasket;
    private Collection<User> collaborators;

    public static TaskBuilder builder() {
        return new TaskBuilder();
    }
}

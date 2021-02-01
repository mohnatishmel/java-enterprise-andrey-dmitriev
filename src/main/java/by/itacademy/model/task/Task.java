package by.itacademy.model.task;

import by.itacademy.model.user.User;
import lombok.*;

import java.util.Collection;
import java.util.Date;

@Data
@Builder
public class Task {

    private final int id;
    private final int userId;
    private TaskInformation taskInfo;
    private Date deaLine;
    private boolean fixed;
    private boolean inBasket;
    private Collection<User> collaborators;
}

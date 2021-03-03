package by.itacademy.model.task;

import by.itacademy.model.user.User;
import lombok.*;

import java.util.Collection;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Task {

    private int id;
    private transient int userId;
    private String name;
    private String description;
    private Date deadLine;
    private boolean fixed;
    private boolean inBasket;

    public static TaskBuilder builder() {
        return new TaskBuilder();
    }
}

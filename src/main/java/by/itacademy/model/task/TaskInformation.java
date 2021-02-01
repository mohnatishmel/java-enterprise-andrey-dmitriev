package by.itacademy.model.task;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class TaskInformation {

    private final int id;
    private String description;
    private String filepath;
}

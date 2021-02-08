package by.itacademy.model.task;

import by.itacademy.model.Unique;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class TaskInformation implements Unique {

    private int id;
    private String description;
    private String filepath;
}

package by.itacademy.model.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class TaskInformation {

    private int id;
    private String description;
    private String filepath;
}

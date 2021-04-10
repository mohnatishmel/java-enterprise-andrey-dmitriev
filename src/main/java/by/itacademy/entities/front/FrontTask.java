package by.itacademy.entities.front;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@Builder

public class FrontTask {

    private int id;
    private int userId;
    private String name;
    private String description;
    private Date deadLine;
    private boolean fixed;
    private boolean inBasket;

}

package by.itacademy.entities.message;

import by.itacademy.entities.Unique;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class UnlockRequestMessage implements Unique {
    private int id;
    private int userId;
    private String userName;
    private String messageBody;
}

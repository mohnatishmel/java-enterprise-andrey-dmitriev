package by.itacademy.model.user;

import by.itacademy.model.Unique;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Credential implements Unique {

    private int id;
    private String login;
    private String password;
}

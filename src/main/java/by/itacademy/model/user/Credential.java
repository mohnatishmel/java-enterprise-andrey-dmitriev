package by.itacademy.model.user;

import by.itacademy.model.Unique;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Credential implements Unique {

    public Credential(String login, String password) {
        this.login = login;
        this.password = password;
    }

    private int id;
    private String login;
    private String password;
}

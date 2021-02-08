package by.itacademy.model.user;

import by.itacademy.model.Unique;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PersonalInformation implements Unique {

    private int id;
    private String firstName;
    private String lastName;
}

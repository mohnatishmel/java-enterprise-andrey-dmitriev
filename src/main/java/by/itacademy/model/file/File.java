package by.itacademy.model.file;

import by.itacademy.model.Unique;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data

public class File implements Unique {
    private long id;
    private byte[] bytes;
    private String name;

    @Override
    public void setId(int id) {

    }
}

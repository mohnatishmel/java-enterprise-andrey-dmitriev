package by.itacademy.entities.file;

import by.itacademy.entities.Unique;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
@Table(name = "TASK_FILES")

public class File implements Unique {

    @Id
    @Column(name = "task_file_id")
    private long id;
    @Column(name = "file")
    private byte[] bytes;
    @Column(name = "file_name")
    private String name;

    @Override
    public void setId(int id) {
        this.id = id;
    }
}

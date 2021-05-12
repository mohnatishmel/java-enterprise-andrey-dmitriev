package by.itacademy.entities.front;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class FrontPage<T> {
    private int number;
    private List<T> content;
    private long total;
}

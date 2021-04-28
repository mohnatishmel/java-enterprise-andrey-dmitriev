package by.itacademy.entities.front;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class FrontPage<T> {
    private List<T> content;
    private long total;
}

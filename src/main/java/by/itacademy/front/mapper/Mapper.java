package by.itacademy.front.mapper;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface Mapper<T> {

    T map(HttpServletRequest request) throws IOException;
}

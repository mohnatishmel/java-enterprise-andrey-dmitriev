package by.itacademy.taskcore.dao;

import by.itacademy.taskcore.exception.DaoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileDaoTest {

    Dao dao;
    List<String> actual;
    List<String> expected;

    @BeforeEach
    void init() {
        dao = new FileDao();
        expected = new ArrayList<>(4);

        expected.add("1 ms");
        expected.add("1 mph");
        expected.add("2 kmh");
        expected.add("3 kn");
    }

    @Test
    void positiveRead() throws DaoException {
        actual = dao.read("test.txt");
        assertEquals(actual, expected);
    }

    @Test
    void negativeRead() {
        assertThrows(DaoException.class, () -> dao.read("fileNotExist.txt"));
    }
}
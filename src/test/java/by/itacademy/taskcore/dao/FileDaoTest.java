package by.itacademy.taskcore.dao;

import by.itacademy.taskcore.exception.DaoException;
import by.itacademy.taskcore.model.speed.Speed;
import by.itacademy.taskcore.model.speed.SpeedContainer;
import by.itacademy.taskcore.validator.DaoDataValidator;
import by.itacademy.taskcore.validator.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class FileDaoTest {

    Dao<SpeedContainer> dao;
    List<SpeedContainer> actual;
    List<SpeedContainer> expected;
    Validator<String> validatorMock;
    @BeforeEach
    void init() {
        validatorMock = Mockito.mock(DaoDataValidator.class);
        Mockito.when(validatorMock.validate(any())).thenReturn(true);
        dao = new FileDao(validatorMock);
        expected = new ArrayList<>(4);

        expected.add(new SpeedContainer( new Speed(1,"ms")));
        expected.add(new SpeedContainer( new Speed(1,"mph")));
        expected.add(new SpeedContainer( new Speed(2,"kmh")));
        expected.add(new SpeedContainer( new Speed(3,"kn")));
    }

    @Test
    void positiveRead() throws DaoException {
        actual = dao.read("test/daoTest.txt");
        assertEquals(actual, expected);
    }

    @Test
    void negativeRead() {
        assertThrows(DaoException.class, () -> dao.read("fileNotExist.txt"));
    }
}
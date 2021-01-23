package by.itacademy.taskcore.service;

import by.itacademy.taskcore.dao.Dao;
import by.itacademy.taskcore.dao.FileDao;
import by.itacademy.taskcore.exception.DaoException;
import by.itacademy.taskcore.model.speed.Speed;
import by.itacademy.taskcore.model.speed.SpeedContainer;
import by.itacademy.taskcore.util.converter.SpeedConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


class SpeedServiceTest {

    SpeedService service;
    Dao<SpeedContainer> fileDaoMock;
    SpeedConverter converterMock;
    List<String> daoDataStringList;
    String fileName;
    String wrongFileName;

    @BeforeEach
    void init() throws DaoException {
        fileName = "fileName.txt";
        wrongFileName = "wrongFileName.txt";
        daoDataStringList = Arrays.asList("1 ms", "1 kn", "2 mph", "3 kmh");

        fileDaoMock = Mockito.mock(FileDao.class);
        Mockito.when(fileDaoMock.read(wrongFileName)).thenThrow(DaoException.class);

        List<SpeedContainer> list = Arrays.asList(
                new SpeedContainer( new Speed(1, "ms")),
                new SpeedContainer( new Speed(1, "kn")),
                new SpeedContainer( new Speed(2, "mph")),
                new SpeedContainer( new Speed(3, "kmh")),
                new SpeedContainer( new Speed(2, "kmh")));
        list.get(1).add(new Speed(0.514444444,"ms"));
        list.get(2).add(new Speed(0.893888889,"ms"));
        list.get(3).add(new Speed(0.833333333,"ms"));
        list.get(4).add(new Speed(2.0 * 1000.0 / 3600.0,"ms"));

        converterMock = Mockito.mock(SpeedConverter.class);
        Mockito.when(converterMock.convertToMetersPerSecond(Collections.singletonList(any())))
                .thenReturn(list);

        service = new SpeedService(fileDaoMock, converterMock);
    }

    @Test
    void loadDataPositive() {
        assertTrue(service.loadData(fileName));
    }

    @Test
    void loadDataNegative() {
        assertFalse(service.loadData(wrongFileName));
    }

    @Test
    void readData() {
        String expected = "1.000 ms = 1.000 ms\n" +
                "1.000 kn = 0.514 ms\n" +
                "2.000 mph = 0.894 ms\n" +
                "3.000 kmh = 0.833 ms\n" +
                "2.000 kmh = 0.556 ms";
        assertEquals(expected, service.readData());
    }

    @Test
    void absolutSort() {
        String expected = "1.000 kn\n" +
                "2.000 kmh\n" +
                "3.000 kmh\n" +
                "2.000 mph\n" +
                "1.000 ms";
        assertEquals(expected, service.absolutSort());
    }

    @Test
    void findSpeedEqualToTwoKilometersPerHour() {
        assertEquals("yes", service.findSpeedEqualToTwoKilometersPerHour());
    }

    @Test
    void relativeRevertedSort() {
        String expected = "3.000 kmh\n" +
                "2.000 kmh\n" +
                "2.000 mph\n" +
                "1.000 kn\n" +
                "1.000 ms";
        assertEquals(expected, service.relativeRevertedSort());
    }
}
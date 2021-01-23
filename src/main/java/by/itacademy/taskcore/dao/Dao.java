package by.itacademy.taskcore.dao;

import by.itacademy.taskcore.exception.DaoException;
import by.itacademy.taskcore.model.Measurement;
import by.itacademy.taskcore.model.MeasurementContainer;


import java.util.List;

public interface Dao <T extends MeasurementContainer<? extends  Measurement>> {

    List<T> read(String name) throws DaoException;
}

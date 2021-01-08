package by.itacademy.taskcore.model;

public interface Convertible extends MeasurementContainer{

    Convertible clone();

    void setValue(double value);

    void setUnit(String unit);
}
